package com.hk.mvipractice.commons.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hk.mvipractice.contracts.BaseContract
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.jvm.internal.Intrinsics
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.memberFunctions

/**
 * Created by hassankhamis on 3,March,2022
 */

// send specific event or state for screen
abstract class BaseFragment<State: BaseContract.BaseState, Effect: BaseContract.BaseEffect, VM: BaseViewModel<out BaseContract.BaseEvent>>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    private var isCallBaseStates = false

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMVIObservers()
//        observeEffects()
        observeStates()
        observeEffects()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Override_Fn", "handleCustomLoading: ${handleCustomLoading()}")
        Log.d("Override_Fn", "handleCustomError: ${handleCustomError()}")
        //(activity as BaseActivity).success
    }


    open fun initMVIObservers() {
        isCallBaseStates = true
        // repeat on life cycle

    }

    private fun observeStates() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    if (isCallBaseStates) {
                        when (it) {
                            BaseContract.BaseState.Idle -> {
                                Log.d("MVI_Practice", "State: Idle for ${this@BaseFragment}")
                            }
                            BaseContract.BaseState.ShimmerLoading -> {
                                Log.d(
                                    "MVI_Practice",
                                    "State: ShimmerLoading for ${this@BaseFragment}"
                                )
                                // TODO Show Loading
                            }
                            BaseContract.BaseState.Loading -> {
                                Log.d("MVI_Practice", "State: Loading for ${this@BaseFragment}")
                                if (overridesHandleCustomLoading()) {
                                    handleCustomLoading()
                                } else {
                                    // Show our default loading
                                }
                                // TODO Show Loading
                            }
                            is BaseContract.BaseState.Success -> {
                                handleNetworkError(false)
                                toggleEmptyPlaceHolder(it.noData)
                                Log.d("MVI_Practice", "State: Success for ${this@BaseFragment}")
                            }
                            is BaseContract.BaseState.ErrorState -> {
                                handleNetworkError(true)
                            }
                            else -> {
                                (it as? State)?.let { state ->
                                    collectState(state)
                                }
                            }
                        }
                    } else {
                        (it as? State)?.let { state ->
                            kotlin.runCatching { collectState(state) }
                        }
                    }
                }
            }
        }
    }

    private fun observeEffects() {
        lifecycleScope.launchWhenCreated {
            viewModel.effect.collect {
                if (isCallBaseStates) {
                    when (it) {
                        is BaseContract.BaseEffect.ErrorDialog -> {
                            Log.d("MVI_Practice", "Effect: ErrorDialog for ${this@BaseFragment}")
                            handleCustomError()
                        }
                        is BaseContract.BaseEffect.SuccessDialog -> {
                            Log.d("MVI_Practice", "Effect: SuccessDialog for ${this@BaseFragment}")
                        }
                        else -> {
                            (it as? Effect)?.let { effect ->
                                collectEffect(effect)
                            }
                        }
                    }
                } else {
                    (it as? Effect)?.let { effect ->
                        collectEffect(effect)
                    }
                }
            }
        }
    }

    abstract fun collectState(state: State)
    abstract fun collectEffect(effect: Effect)
    open fun handleCustomLoading() {}
    open fun handleCustomError() {}
    open fun toggleEmptyPlaceHolder(noData: Boolean){}
    open fun handleNetworkError(showError: Boolean){}

    private fun overridesHandleCustomLoading(cls: KClass<*> = this::class) =
        cls.memberFunctions.first { it.name == "handleCustomLoading" } in cls.declaredFunctions

    private fun overridesHandleCustomError(cls: KClass<*> = this::class) =
        cls.memberFunctions.first { it.name == "handleCustomError" } in cls.declaredFunctions
}