package com.hk.mvipractice.commons.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hk.mvipractice.contracts.BaseContract
import kotlinx.coroutines.flow.*

/**
 * Created by hassankhamis on 3,March,2022
 */

abstract class BaseActivity<VM: BaseViewModel<out BaseContract.BaseEvent>> : AppCompatActivity() {

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
//        if (::viewModel.isInitialized.not()) initializeViewModel()
    }

    open fun initObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                when (it) {
                    is BaseContract.BaseState.Idle -> {
                        Log.d("MVI_Practice", "State: Idle")
                    }
                    is BaseContract.BaseState.Loading -> {
                        Log.d("MVI_Practice", "State: Loading")
                        // TODO Show Loading
                    }
                    is BaseContract.BaseState.Success -> {
                        Log.d("MVI_Practice", "State: Success")
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.effect.collect {
                when (it) {
                    is BaseContract.BaseEffect.ErrorDialog -> {
                        Log.d("MVI_Practice", "Effect: ErrorDialog")
                    }
                    is BaseContract.BaseEffect.SuccessDialog -> {
                        Log.d("MVI_Practice", "Effect: ErrorDialog")
                    }
                }
            }
        }

    }
}