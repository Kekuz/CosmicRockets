package com.cosmicrockets.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.presentation.mapper.RocketFragmentMapper
import com.cosmicrockets.ui.rocket.RocketFragment
import com.cosmicrockets.ui.rocket.ViewPagerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val searchRocketsUseCase: SearchRocketsUseCase,
): ViewModel() {

    private val _rocketFragmentsLiveData = MutableLiveData<List<RocketFragment>>()
    val rocketFragmentsLiveData: LiveData<List<RocketFragment>> = _rocketFragmentsLiveData

    private val _placeholderLiveData = MutableLiveData<String>()
    val placeholderLiveData: LiveData<String> = _placeholderLiveData
    init {
        request()
    }
    private fun request(){
        searchRocketsUseCase.execute(object : SearchRocketsUseCase.RocketConsumer {
            override fun consume(foundRockets: List<Rocket>?, errorMessage: String?) {
                CoroutineScope(Dispatchers.IO).launch {
                    if (foundRockets != null) {
                        Log.e("Response", foundRockets.toString())
                        _rocketFragmentsLiveData.postValue(RocketFragmentMapper.map(foundRockets))
                    }
                    if (errorMessage != null) {
                        _placeholderLiveData.postValue(errorMessage.toString())
                    } else {
                        _placeholderLiveData.postValue("-")
                    }
                }
            }

        })
    }

}