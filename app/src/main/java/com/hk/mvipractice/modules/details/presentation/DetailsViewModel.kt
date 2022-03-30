package com.hk.mvipractice.modules.details.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hk.mvipractice.commons.presentation.BaseViewModel
import com.hk.mvipractice.contracts.BaseContract
import com.hk.mvipractice.contracts.DetailsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by hassankhamis on 23,February,2022
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(): BaseViewModel<DetailsContract.DetailsEvent>() {

    override fun handleCustomEvent(event: DetailsContract.DetailsEvent) {
        Log.d("MVI_Practice", "Event: AddToCart")

        viewModelScope.launch {
            when(event) {

            }
        }

    }

    override fun createInitialState(): BaseContract.BaseState {
        return BaseContract.BaseState.Idle
    }
}