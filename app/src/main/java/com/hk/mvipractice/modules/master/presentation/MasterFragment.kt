package com.hk.mvipractice.modules.master.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hk.mvipractice.R
import com.hk.mvipractice.commons.presentation.BaseFragment
import com.hk.mvipractice.contracts.BaseContract
import com.hk.mvipractice.contracts.MasterContract
import com.hk.mvipractice.databinding.FragmentMasterBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hassankhamis on 03,March,2022
 */
@AndroidEntryPoint
class MasterFragment : BaseFragment<MasterContract.MasterState, MasterContract.MasterEffect, MasterViewModel>(R.layout.fragment_master) {
    override val viewModel: MasterViewModel by viewModels<MasterViewModel>()
    lateinit var binding: FragmentMasterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMasterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()

    }


    fun setListeners() {
        binding.apply {
            addToCart.setOnClickListener {
                viewModel.setEvent(MasterContract.MasterEvent.AddToCart)

            }
        }
    }

    override fun initMVIObservers() {
//        super.initMVIObservers()
    }

    override fun collectState(state: MasterContract.MasterState) {
        when (state) {
            MasterContract.MasterState.BindData -> {
                Log.d("MVI_Practice", "child State: BindData for ${this@MasterFragment}")
//                        openDetailsScreen()
            }
        }
    }

    override fun collectEffect(effect: MasterContract.MasterEffect) {
        when (effect) {
            MasterContract.MasterEffect.OpenDetails -> {
                Log.d("MVI_Practice", "child Effect: OpenDetails for ${this@MasterFragment}")
                openDetailsScreen()
            }
        }
    }
}