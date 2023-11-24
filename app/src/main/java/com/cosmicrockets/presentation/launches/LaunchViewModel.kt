package com.cosmicrockets.presentation.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.ui.state.LaunchesFragmentState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LaunchViewModel(
    private val id: String,
    private val searchLaunchByIdUseCase: SearchLaunchByIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData<LaunchesFragmentState>()
    val state: LiveData<LaunchesFragmentState> = _state


    private var count = 1

    private var isFirstLoad = true

    init {

        request()
    }

    fun request() {
        if (isFirstLoad){
            _state.value = LaunchesFragmentState.Loading
            isFirstLoad = false
        }else{
            _state.value = LaunchesFragmentState.LoadingBottom
        }


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

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            val content = LaunchesFragmentState.Content(getData())
            _state.postValue(content)
        }


    }

    private fun getData(): List<Launch> {
        return listOf(
            Launch(
                "",
                count++.toString(),
                "2 февраля, 2022",
                true
            ),
            Launch(
                "",
                count++.toString(),
                "6 января, 2022",
                true
            ),
            Launch(
                "",
                count++.toString(),
                "23 декабря, 2021",
                false
            ),
            Launch(
                "",
                count++.toString(),
                "2 февраля, 2022",
                true
            ),
            Launch(
                "",
                count++.toString(),
                "6 января, 2022",
                true
            ),
            Launch(
                "",
                count++.toString(),
                "23 декабря, 2021",
                false
            ),
            Launch(
                "",
                count++.toString(),
                "2 февраля, 2022",
                true
            ),
            Launch(
                "",
                count++.toString(),
                "6 января, 2022",
                true
            ),
            Launch(
                "",
                count++.toString(),
                "23 декабря, 2021",
                false
            ),
            Launch(
                "",
                count++.toString(),
                "2 февраля, 2022",
                true
            ),
        )
    }
}