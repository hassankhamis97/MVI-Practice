package com.hk.mvipractice.contracts

/**
 * Created by hassankhamis on 2,March,2022
 */
class DetailsContract {
    // Events that user performed
    sealed class DetailsEvent : BaseContract.BaseEvent() {
        object AddToCart : DetailsEvent()
    }

    sealed class DetailsState: BaseContract.BaseState() {
        object BindData : DetailsState()
    }

    sealed class DetailsEffect: BaseContract.BaseEffect() {
//        object OpenDetails : HomeEffect()
    }
}