package com.hk.mvipractice.commons.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/**
 * Created by hassankhamis on 22,February,2022
 */
abstract class BaseViewModel <State: UiState, Event: UiEvent, Effect: UiEffect>: ViewModel() {

    private val initialState : State by lazy { createInitialState() }

    private val _state = MutableStateFlow<UiState>(initialState)
    val state get() = _state.asStateFlow()

    private val _event = MutableSharedFlow<Event>()
    val event get() = _event.asSharedFlow()

    private val _effect = Channel<Effect>()
    val effect get() = _effect.receiveAsFlow()

    abstract fun createInitialState(): State

}