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
//                DetailsContract.DetailsEffect.AddToCart -> {
//                    setState { copy(state = BaseContract.BaseState.Loading) }
//                    delay(1000)
//                    setState { copy(state = MasterContract.MasterState.BindData) }
//
//                }
            }
        }

    }

    override fun createInitialState(): BaseContract.BaseState {
//        return (HomeContract.HomeState.Super as BaseContract.BaseState).Idle
        return BaseContract.BaseState.Idle
    }
}