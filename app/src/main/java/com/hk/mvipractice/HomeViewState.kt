package com.hk.mvipractice

sealed class HomeViewState {
    object Idle: HomeViewState()
    data class Result(val number: Int): HomeViewState()
    data class Error(val error: String): HomeViewState()
}
