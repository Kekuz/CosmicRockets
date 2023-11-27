package com.cosmicrockets.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.cosmicrockets.databinding.ActivityMainBinding
import com.cosmicrockets.presentation.main.NotificationWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationPermission()

        val workRequestForTime =
            PeriodicWorkRequestBuilder<NotificationWorker>(30, TimeUnit.MINUTES, 25, TimeUnit.MINUTES)
                .addTag("notification")
                .build()

        WorkManager.getInstance(this).cancelAllWorkByTag("notification")
        WorkManager.getInstance(this).enqueue(workRequestForTime)
    }

    private fun notificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }
    }
}