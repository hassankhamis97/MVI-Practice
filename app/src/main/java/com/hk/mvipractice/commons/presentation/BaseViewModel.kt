package com.hk.mvipractice.commons.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hk.mvipractice.contracts.BaseContract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by hassankhamis on 22,February,2022
 */
abstract class BaseViewModel<Event: BaseContract.BaseEvent>: ViewModel() {

    private val initialState : BaseContract.State by lazy { createInitialState() }

    private val _state = MutableStateFlow<BaseContract.State>(initialState)
    val state get() = _state.asStateFlow()

    // Get Current State
    val currentState: BaseContract.State
        get() = state.value

    private val _event = MutableSharedFlow<BaseContract.BaseEvent>()
    val event get() = _event.asSharedFlow()

    private val _effect = Channel<BaseContract.BaseEffect>()
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
                handleBaseEvent(it)
            }
        }
    }

    /**
     * Set new Event
     */

    fun setEvent(event: BaseContract.BaseEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    /**
     * Set new Ui State
     */

    protected fun setState(reduce: BaseContract.State.() -> BaseContract.State) {
        viewModelScope.launch {
            val newState = currentState.reduce()
            _state.value = newState
        }
    }

    protected fun setEffect(builder: () -> BaseContract.BaseEffect) {
        val effect = builder()
        viewModelScope.launch { _effect.send(effect) }
    }

    /**
     * Handle each event
     */
    open fun handleBaseEvent(event : BaseContract.BaseEvent) {
        when (event) {
            BaseContract.BaseEvent.OnRetryDataClicked -> TODO()
            else -> {
                handleCustomEvent(event as Event)
            }
        }

    }

    abstract fun handleCustomEvent(event : Event)

    abstract fun createInitialState(): BaseContract.State
}