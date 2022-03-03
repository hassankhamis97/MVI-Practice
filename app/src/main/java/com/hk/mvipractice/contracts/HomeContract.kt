package com.hk.mvipractice.contracts

/**
 * Created by hassankhamis on 2,March,2022
 */
class HomeContract {
    // Events that user performed
    sealed class HomeEvent : BaseContract.BaseEvent() {
        object AddToCart : HomeEvent()
    }

    sealed class HomeState: BaseContract.BaseState() {
//            object Super: BaseContract.BaseState()
        object BindData : HomeState()
//        object Loading : BaseContract.BaseState()
//        object Success : BaseContract.BaseState()
    }

    sealed class HomeEffect: BaseContract.BaseEffect() {
//        object OpenDetails : HomeEffect()
    }
}