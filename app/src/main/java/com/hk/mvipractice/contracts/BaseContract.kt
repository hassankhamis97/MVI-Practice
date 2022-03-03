package com.hk.mvipractice.contracts

/**
 * Created by hassankhamis on 27,February,2022
 */
class BaseContract {

    // Events that user performed
    sealed class BaseEvent : UiEvent {
        object OnRetryDataClicked : BaseEvent()
    }

    // Ui View States
    data class State(
        val state: BaseState
    ) : UiState

    // View State that related to Most screens
    sealed class BaseState: UiState {
        object Idle: BaseState()
        object Loading: BaseState()
        object Success: BaseState()

    }

    // Side effects

    sealed class BaseEffect : UiEffect {

        object ErrorDialog : BaseEffect()
        object SuccessDialog : BaseEffect()

    }
}