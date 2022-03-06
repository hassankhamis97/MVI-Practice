package com.hk.mvipractice.contracts

/**
 * Created by hassankhamis on 2,March,2022
 */
class HomeContract {
    // Events that user performed
    sealed class HomeEvent : BaseContract.BaseEvent() {}

    sealed class HomeState: BaseContract.BaseState() {}

    sealed class HomeEffect: BaseContract.BaseEffect() {}
}