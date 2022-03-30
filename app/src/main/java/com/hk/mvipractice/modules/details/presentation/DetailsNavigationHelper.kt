package com.hk.mvipractice.modules.master.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.hk.mvipractice.R
import com.hk.mvipractice.commons.presentation.helper.AnimationType
import com.hk.mvipractice.commons.presentation.helper.getNavOptions

/**
 * Created by hassankhamis on 6,March,2022
 */


fun Fragment.openDetailsScreen() {
    val navController = Navigation.findNavController(requireActivity(), R.id.container)
    val args = Bundle()
    navController.navigate(
        R.id.detailsFragment, args, getNavOptions(AnimationType.DEFAULT, singleTop = true)
    )
}