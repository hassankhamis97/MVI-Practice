package com.hk.mvipractice.modules.details.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hk.mvipractice.R
import com.hk.mvipractice.commons.presentation.BaseFragment
import com.hk.mvipractice.contracts.BaseContract
import com.hk.mvipractice.contracts.DetailsContract
import com.hk.mvipractice.contracts.MasterContract
import com.hk.mvipractice.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hassankhamis on 03,March,2022
 */
@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsContract.DetailsState, DetailsContract.DetailsEffect,DetailsViewModel>(R.layout.fragment_details) {
    override val viewModel: DetailsViewModel by viewModels()
    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()

    }


    private fun setListeners() {
        binding.apply {

        }
    }

    override fun collectState(state: DetailsContract.DetailsState) {
        when (state) {
            DetailsContract.DetailsState.BindData -> {
                Log.d("MVI_Practice", "child State: BindData for ${this@DetailsFragment}")
            }
            else -> {}
        }
    }

    override fun initMVIObservers() {

    }

    override fun collectEffect(effect: DetailsContract.DetailsEffect) {
//        TODO("Not yet implemented")
    }
}