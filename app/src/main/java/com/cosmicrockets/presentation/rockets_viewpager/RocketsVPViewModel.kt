package com.cosmicrockets.presentation.rockets_viewpager

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.presentation.mapper.RocketInfoMapper
import com.cosmicrockets.presentation.models.RocketInfo
import com.cosmicrockets.ui.rocket.RocketFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RocketsVPViewModel(
    private val searchRocketsUseCase: SearchRocketsUseCase,
) : ViewModel() {
    private val _rocketFragmentsLiveData = MutableLiveData<List<RocketFragment>>()
    val rocketFragmentsLiveData: LiveData<List<RocketFragment>> = _rocketFragmentsLiveData

    private val _placeholderLiveData = MutableLiveData<String>()
    val placeholderLiveData: LiveData<String> = _placeholderLiveData

    init {
        request()
    }

    private fun request() {
        searchRocketsUseCase.execute(object : SearchRocketsUseCase.RocketConsumer {
            override fun consume(foundRockets: List<Rocket>?, errorMessage: String?) {
                CoroutineScope(Dispatchers.IO).launch {
                    if (foundRockets != null) {
                        //Log.e("Response", foundRockets.toString())

                        _rocketFragmentsLiveData.postValue(getFragmentsFrom(foundRockets.map {
                            RocketInfoMapper.map(
                                it
                            )
                        }))
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

    private fun getFragmentsFrom(rockets: List<RocketInfo>): List<RocketFragment> {
        val rocketFragments = mutableListOf<RocketFragment>()
        for (r in rockets) {
            val fragment = RocketFragment()
            val bundle = Bundle()
            bundle.putParcelable("rocket", r)
            fragment.arguments = bundle
            rocketFragments.add(fragment)
        }
        return rocketFragments
    }
}