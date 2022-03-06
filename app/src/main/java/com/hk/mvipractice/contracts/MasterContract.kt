package com.hk.mvipractice.contracts

/**
 * Created by hassankhamis on 2,March,2022
 */
class MasterContract {
    // Events that user performed
    sealed class MasterEvent : BaseContract.BaseEvent() {
        object AddToCart : MasterEvent()
    }

    sealed class MasterState: BaseContract.BaseState() {
        object BindData : MasterState()
    }

    sealed class MasterEffect: BaseContract.BaseEffect() {
        object OpenDetails : MasterEffect()
    }
}

//