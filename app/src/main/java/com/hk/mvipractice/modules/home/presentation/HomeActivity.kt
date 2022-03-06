package com.hk.mvipractice.modules.home.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBindings
import com.hk.mvipractice.R
import com.hk.mvipractice.commons.presentation.BaseActivity
import com.hk.mvipractice.contracts.BaseContract
import com.hk.mvipractice.contracts.HomeContract
import com.hk.mvipractice.databinding.ActivityHomeBinding
import com.hk.mvipractice.modules.home.domain.usecase.HomeUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterIsInstance
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel>() {

    override val viewModel by viewModels<HomeViewModel>()

    lateinit var bindings: ActivityHomeBinding
//
//    @Inject
//    lateinit var homeUseCase: HomeUseCase
//
//    @Inject
//    lateinit var homeUseCase2: HomeUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindings.root)
//        Log.d("TestHilt", "ref of : $homeUseCase")
//        Log.d("TestHilt", "ref of : $homeUseCase2")
    }

}