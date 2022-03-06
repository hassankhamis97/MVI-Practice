package com.hk.mvipractice.modules.home.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hk.mvipractice.commons.presentation.BaseViewModel
import com.hk.mvipractice.contracts.BaseContract
import com.hk.mvipractice.contracts.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by hassankhamis on 23,February,2022
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val homeUseCase: HomeUseCase,
        ): BaseViewModel<HomeContract.HomeEvent>() {

   init {
//       Log.d("TestHilt", "ref of : $homeUseCase")
//       Log.d("TestHilt", "ref of : $homeUseCase2")
//       homeUseCase.toString()
   }

    override fun handleCustomEvent(event: HomeContract.HomeEvent) {

    }

    override fun createInitialState(): BaseContract.State {
//        return (HomeContract.HomeState.Super as BaseContract.BaseState).Idle
        return BaseContract.State(BaseContract.BaseState.Idle)
    }
}