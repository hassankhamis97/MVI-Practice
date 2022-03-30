package com.hk.mvipractice.commons.presentation

import android.util.Log
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

    private val initialState : BaseContract.BaseState by lazy { createInitialState() }

    private val _state = MutableStateFlow(initialState)
    val state get() = _state.asStateFlow()

//    // Get Current State
//    val currentState: BaseContract
//        get() = state

    private val _event = MutableSharedFlow<Event>()
    private val event get() = _event.asSharedFlow()

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

    fun setEvent(event: Event) {
        viewModelScope.launch {

            Log.d("MVI_Practice", "setEvent subscriptionCount: ${_event.subscriptionCount.value}")
            _event.emit(event)
        }
    }

    /**
     * Set new Ui State
     */

    protected fun setState(builder: () -> BaseContract.BaseState) {
        viewModelScope.launch {
            Log.d("MVI_Practice", "setState coroutineScope: $this")
            Log.d("MVI_Practice", "setState subscriptionCount: ${_state.subscriptionCount.value}")
            _state.emit(builder())
        }
    }

    protected fun setEffect(builder: () -> BaseContract.BaseEffect) {
        val effect = builder()
        viewModelScope.launch { _effect.send(effect) }
    }

    /**
     * Handle each event
     */
    private fun handleBaseEvent(event : BaseContract.BaseEvent) {
        when (event) {
            BaseContract.BaseEvent.OnRetryDataClicked -> TODO()
            else -> {
                handleCustomEvent(event as Event)
            }
        }

    }

    abstract fun handleCustomEvent(event : Event)

    abstract fun createInitialState(): BaseContract.BaseState
}