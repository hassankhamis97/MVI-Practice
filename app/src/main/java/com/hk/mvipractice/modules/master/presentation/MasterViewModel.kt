package com.hk.mvipractice.modules.master.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hk.mvipractice.commons.presentation.BaseViewModel
import com.hk.mvipractice.contracts.BaseContract
import com.hk.mvipractice.contracts.MasterContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by hassankhamis on 23,February,2022
 */
@HiltViewModel
class MasterViewModel @Inject constructor(): BaseViewModel<MasterContract.MasterEvent>() {

    override fun handleCustomEvent(event: MasterContract.MasterEvent) {
        Log.d("MVI_Practice", "Event: AddToCart")

        viewModelScope.launch {
            when(event) {
                MasterContract.MasterEvent.AddToCart -> {
                    setState { BaseContract.BaseState.Loading }
                    delay(1000)
                    setState { MasterContract.MasterState.BindData }
                    setEffect {
                        MasterContract.MasterEffect.OpenDetails
                    }
                }
            }
        }

    }

    override fun createInitialState(): BaseContract.BaseState {
        return BaseContract.BaseState.Idle
    }
}