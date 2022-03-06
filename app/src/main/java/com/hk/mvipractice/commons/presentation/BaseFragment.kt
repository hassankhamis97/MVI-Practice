package com.hk.mvipractice.commons.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.hk.mvipractice.contracts.BaseContract
import kotlinx.coroutines.flow.*

/**
 * Created by hassankhamis on 3,March,2022
 */


// send specific event or state for screen
abstract class BaseFragment<VM: BaseViewModel<out BaseContract.BaseEvent>>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        //(activity as BaseActivity).success
    }



    open fun initObservers() {
        // repeat on life cycle
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                when (it.state) {
                    BaseContract.BaseState.Idle -> {
                        Log.d("MVI_Practice", "State: Idle for ${this@BaseFragment}")
                    }
                    BaseContract.BaseState.ShimmerLoading -> {
                        Log.d("MVI_Practice", "State: ShimmerLoading for ${this@BaseFragment}")
                        // TODO Show Loading
                    }
                    BaseContract.BaseState.Loading -> {
                        Log.d("MVI_Practice", "State: Loading for ${this@BaseFragment}")
                        handleCustomLoading()
                        // TODO Show Loading
                    }
                    BaseContract.BaseState.Success -> {
                        Log.d("MVI_Practice", "State: Success for ${this@BaseFragment}")
                    }
                    else -> collectState(it.state)
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.effect.collect {
                when (it) {
                    BaseContract.BaseEffect.ErrorDialog -> {
                        Log.d("MVI_Practice", "Effect: ErrorDialog for ${this@BaseFragment}")
                        handleCustomError()
                    }
                    BaseContract.BaseEffect.SuccessDialog -> {
                        Log.d("MVI_Practice", "Effect: SuccessDialog for ${this@BaseFragment}")
                    }
                    else -> {
                        collectEffect(it)
                    }
                }
            }
        }

    }

    abstract fun collectState(state: BaseContract.BaseState)
    abstract fun collectEffect(effect: BaseContract.BaseEffect)
    open fun handleCustomLoading(){}
    open fun handleCustomError(){
        // show top dialog

    }
}