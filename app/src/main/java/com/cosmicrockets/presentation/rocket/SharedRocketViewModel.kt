package com.cosmicrockets.presentation.rocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmicrockets.ui.state.RocketRecyclerState

class SharedRocketViewModel: ViewModel() {

    private val _state = MutableLiveData<RocketRecyclerState>()
    val state: LiveData<RocketRecyclerState> = _state


    fun setState(newState: RocketRecyclerState){
        _state.value = newState
    }


}