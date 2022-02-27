package com.hk.mvipractice.commons.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by hassankhamis on 22,February,2022
 */
abstract class BaseViewModel <State: UiState, Event: UiEvent, Effect: UiEffect>: ViewModel() {

    private val initialState : State by lazy { createInitialState() }

    private val _state = MutableStateFlow<State>(initialState)
    val state get() = _state.asStateFlow()

    // Get Current State
    val currentState: State
        get() = state.value

    private val _event = MutableSharedFlow<Event>()
    val event get() = _event.asSharedFlow()

    private val _effect = Channel<Effect>()
    val effect get() = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Set new Event
     */

    fun setEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    /**
     * Set new Ui State
     */

    protected fun setState(reduce: State.() -> State) {
        viewModelScope.launch {
            val newState = currentState.reduce()
            _state.value = newState
        }
    }

    protected fun setEffect(builder: () -> Effect) {
        val effect = builder()
        viewModelScope.launch { _effect.send(effect) }
    }

    /**
     * Handle each event
     */
    abstract fun handleEvent(event : Event)

    abstract fun createInitialState(): State
}