package com.hk.mvipractice.modules.home.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.hk.mvipractice.R
import com.hk.mvipractice.contracts.HomeContract
import com.hk.mvipractice.modules.home.domain.usecase.HomeUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var homeUseCase: HomeUseCase

    @Inject
    lateinit var homeUseCase2: HomeUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("TestHilt", "ref of : $homeUseCase")
        Log.d("TestHilt", "ref of : $homeUseCase2")
        viewModel.setEvent(HomeContract.HomeEvent.OnOpenDetailsClicked)
    }
}