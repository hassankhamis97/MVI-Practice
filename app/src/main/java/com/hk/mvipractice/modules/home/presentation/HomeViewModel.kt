package com.hk.mvipractice.modules.home.presentation

import com.hk.mvipractice.commons.presentation.BaseViewModel
import com.hk.mvipractice.contracts.BaseContract
import com.hk.mvipractice.contracts.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by hassankhamis on 23,February,2022
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val homeUseCase: HomeUseCase,
//    private val homeUseCase2: HomeUseCase,
        ): BaseViewModel<HomeContract.HomeState, HomeContract.HomeEvent, HomeContract.HomeEffect>() {

   init {
//       Log.d("TestHilt", "ref of : $homeUseCase")
//       Log.d("TestHilt", "ref of : $homeUseCase2")
//       homeUseCase.toString()
   }

    override fun handleEvent(event: HomeContract.HomeEvent) {

    }

    override fun createInitialState(): HomeContract.HomeState {
//        return (HomeContract.HomeState.Super as BaseContract.BaseState).Idle
        return HomeContract.HomeState.Idle

    }
}