package com.cosmicrockets.presentation.launches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmicrockets.domain.api.interactor.DatabaseInteractor
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.domain.models.launch.LaunchResponse
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.ui.state.LaunchesFragmentState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random
import javax.inject.Inject

class LaunchViewModel(
    private val rocketId: String,
    private val searchLaunchByIdUseCase: SearchLaunchByIdUseCase,
    private val databaseInteractor: DatabaseInteractor,
) : ViewModel() {

    private val _state = MutableLiveData<LaunchesFragmentState>()
    val state: LiveData<LaunchesFragmentState> = _state

    private var isFirstLoad = true
    var hasNextPage = true
    private var pageNumber = 1


    init {
        request()
    }

    fun request() {
        if (isFirstLoad) {
            _state.value = LaunchesFragmentState.Loading
            isFirstLoad = false
        } else {
            _state.value = LaunchesFragmentState.LoadingBottom
        }


        searchLaunchByIdUseCase.execute(
            pageNumber,
            rocketId,
            object : SearchLaunchByIdUseCase.LaunchConsumer {
                override fun consume(launchResponse: LaunchResponse?, errorMessage: String?) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (launchResponse != null) {
                            val content = LaunchesFragmentState.Content(launchResponse.docs)
                            _state.postValue(content)
                            hasNextPage = launchResponse.hasNextPage
                            if (hasNextPage) pageNumber++
                            databaseInteractor.saveLaunches(launchResponse.docs)
                        }
                        if (errorMessage != null) {
                            getLaunchesFromDatabase(rocketId, errorMessage)
                        }
                    }
                }

            })

    }

    private fun getLaunchesFromDatabase(rocketId: String, errorMessage: String) {
        databaseInteractor.getLaunches(rocketId, object : DatabaseInteractor.DatabaseConsumer {
            override fun consume(foundLaunches: List<Launch>) {
                CoroutineScope(Dispatchers.IO).launch {
                    Log.d("Launches from DB", foundLaunches.toString())
                    val error = LaunchesFragmentState.Error(foundLaunches, errorMessage)
                    _state.postValue(error)
                }
            }

        })
    }

    fun recyclerRefreshed(){
        hasNextPage = true
        pageNumber = 1
        isFirstLoad = true
        request()
    }

}