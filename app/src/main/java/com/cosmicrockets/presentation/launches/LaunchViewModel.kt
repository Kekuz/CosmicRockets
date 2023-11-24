package com.cosmicrockets.presentation.launches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.ui.state.LaunchesFragmentState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaunchViewModel(
    private val id: String,
    private val searchLaunchByIdUseCase: SearchLaunchByIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData<LaunchesFragmentState>()
    val state: LiveData<LaunchesFragmentState> = _state


    init {
        request()
    }

    private fun request() {
        _state.value = LaunchesFragmentState.Loading

        /* searchLaunchByIdUseCase.execute(id, object : SearchLaunchByIdUseCase.LaunchConsumer {
             override fun consume(foundLaunches: List<Launch>?, errorMessage: String?) {
                 CoroutineScope(Dispatchers.IO).launch {
                     if (foundLaunches != null) {
                         val content = LaunchesFragmentState.Content(foundLaunches)
                         _state.postValue(content)
                     }
                     if (errorMessage != null) {
                         val error = LaunchesFragmentState.Error(errorMessage)
                         _state.postValue(error)
                     }
                 }
             }

         })*/
        val mockup = listOf(
            Launch(
                "",
                "FalconSat",
                "2 февраля, 2022",
                true
            ),
            Launch(
                "",
                "Heavy holidays",
                "6 января, 2022",
                true
            ),
            Launch(
                "",
                "CRS-24 Mission",
                "23 декабря, 2021",
                false
            ),
        )


        val content = LaunchesFragmentState.Content(mockup)
        _state.postValue(content)


    }
}