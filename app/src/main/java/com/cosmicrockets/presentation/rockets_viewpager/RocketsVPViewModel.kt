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
import com.cosmicrockets.ui.state.RocketsVPFragmentState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RocketsVPViewModel(
    private val searchRocketsUseCase: SearchRocketsUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<RocketsVPFragmentState>()
    val state: LiveData<RocketsVPFragmentState> = _state


    init {
        request()
    }

    fun request() {
        _state.value = RocketsVPFragmentState.Loading

        searchRocketsUseCase.execute(object : SearchRocketsUseCase.RocketConsumer {
            override fun consume(foundRockets: List<Rocket>?, errorMessage: String?) {
                CoroutineScope(Dispatchers.IO).launch {
                    if (foundRockets != null) {
                        //Log.e("Response", foundRockets.toString())
                        val rocketFragments = getFragmentsFrom(foundRockets.map {
                            RocketInfoMapper.map(
                                it
                            )
                        })
                        val content = RocketsVPFragmentState.Content(rocketFragments)
                        _state.postValue(content)
                    }
                    if (errorMessage != null) {
                        val error = RocketsVPFragmentState.Error(errorMessage)
                        _state.postValue(error)
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