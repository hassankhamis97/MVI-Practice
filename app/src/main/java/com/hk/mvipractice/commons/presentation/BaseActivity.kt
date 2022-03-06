package com.hk.mvipractice.commons.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.hk.mvipractice.R
import com.hk.mvipractice.contracts.BaseContract
import com.hk.mvipractice.contracts.HomeContract
import com.hk.mvipractice.modules.home.domain.usecase.HomeUseCase
import com.hk.mvipractice.modules.home.presentation.HomeViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.system.exitProcess

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
                when (it.state) {
                    BaseContract.BaseState.Idle -> {
                        Log.d("MVI_Practice", "State: Idle")
                    }
                    BaseContract.BaseState.Loading -> {
                        Log.d("MVI_Practice", "State: Loading")
                        // TODO Show Loading
                    }
                    BaseContract.BaseState.Success -> {
                        Log.d("MVI_Practice", "State: Success")
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.effect.collect {
                when (it) {
                    BaseContract.BaseEffect.ErrorDialog -> {
                        Log.d("MVI_Practice", "Effect: ErrorDialog")
                    }
                    BaseContract.BaseEffect.SuccessDialog -> {
                        Log.d("MVI_Practice", "Effect: ErrorDialog")
                    }
                }
            }
        }

    }
}