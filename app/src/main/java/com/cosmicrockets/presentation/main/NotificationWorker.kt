package com.cosmicrockets.presentation.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.cosmicrockets.R
import com.cosmicrockets.app.App
import com.cosmicrockets.databinding.ActivityMainBinding
import com.cosmicrockets.domain.api.interactor.DatabaseInteractor
import com.cosmicrockets.domain.api.usecase.SearchLastLaunchesUseCase
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.domain.models.launch.LaunchResponse
import com.cosmicrockets.ui.main.MainActivity
import com.cosmicrockets.ui.state.LaunchesFragmentState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotificationWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {
    @Inject
    lateinit var searchLastLaunchesUseCase: SearchLastLaunchesUseCase

    @Inject
    lateinit var databaseInteractor: DatabaseInteractor

    private val _launches = MutableLiveData<List<Launch>>()
    val launches: LiveData<List<Launch>> = _launches

    init {
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun doWork(): Result {
        Log.e("NotificationWorker", "Notified")

        searchLastLaunchesUseCase.execute(
            1,
            object : SearchLastLaunchesUseCase.LaunchConsumer {
                override fun consume(launchResponse: LaunchResponse?, errorMessage: String?) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (launchResponse != null) {
                            databaseInteractor.getLaunchById(
                                launchResponse.docs[0].id,
                                object : DatabaseInteractor.DatabaseLaunchConsumer {
                                    override fun consume(foundLaunch: Launch?) {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            if (foundLaunch == null) {
                                                makeNotification(launchResponse.docs[0])
                                                databaseInteractor.saveLaunches(listOf(launchResponse.docs[0]))
                                            }
                                        }
                                    }
                                })
                            Result.success()
                        }
                        if (errorMessage != null) {
                            Result.retry()
                        }
                    }
                }
            })
        return Result.success()
    }


    private fun makeNotification(launch: Launch) {
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Новый космический запуск ${launch.name} cостоялся ${launch.date}!")
            .setContentText("Нажмите чтобы посмотреть подробности...")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        //До диплинка я не додумался :((((((
        //По этому просто открываем приложение при нажатии на уведомление
        //Что поделать
        val intent = Intent(applicationContext, MainActivity::class.java)

        val pendingIntent =
            PendingIntent.getActivity(
                applicationContext, 0, intent,
                PendingIntent.FLAG_MUTABLE
            )
        builder.setContentIntent(pendingIntent)

        val notificationManager: NotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel =
                notificationManager.getNotificationChannel(CHANNEL_ID)
            if (notificationChannel == null) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                notificationChannel =
                    NotificationChannel(CHANNEL_ID, "Some description", importance)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }
        notificationManager.notify(0, builder.build())
    }

    companion object {
        const val CHANNEL_ID = "channelID"
    }
}