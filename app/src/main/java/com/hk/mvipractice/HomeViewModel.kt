package com.hk.mvipractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    val intent: MutableSharedFlow<HomeIntent> = MutableSharedFlow()


    private val mState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState.Idle)
    val state: StateFlow<HomeViewState> get() = mState

    private var number = 0

    init {
        process()
    }

    private fun process() {
        viewModelScope.launch {
            intent.collect {
                when(it) {
                    HomeIntent.AddNumber -> {
                        addNumber()
                    }
                }
            }
        }

    }

    private fun addNumber() {
        viewModelScope.launch {
            try {
                mState.emit(HomeViewState.Result(++number))
            } catch (ex: Exception) {
                mState.emit(HomeViewState.Error(ex.localizedMessage))
            }
        }
    }
}