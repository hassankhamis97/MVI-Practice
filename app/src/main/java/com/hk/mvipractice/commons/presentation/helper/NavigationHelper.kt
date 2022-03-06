package com.hk.mvipractice.commons.presentation.helper

import androidx.annotation.IdRes
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import com.hk.mvipractice.R

fun getNavOptions(
    animationType: AnimationType = AnimationType.SLIDE,
    @IdRes popTo: Int? = null,
    inclusivePop: Boolean = true,
    singleTop: Boolean = false
): NavOptions {
    return navOptions {
        launchSingleTop = singleTop
        if (popTo != null) {
            popUpTo(popTo) {
                inclusive = inclusivePop
            }
        }
        when (animationType) {
            AnimationType.DEFAULT -> {
                getDefaultAnimation()
            }
        }
    }
}
enum class AnimationType {
    SLIDE, BOTTOM_SHEET,DEFAULT
}

    private fun NavOptionsBuilder.getDefaultAnimation() {
    anim {
        enter = R.anim.nav_default_enter_anim
        exit = R.anim.nav_default_exit_anim
        popEnter = R.anim.nav_default_pop_enter_anim
        popExit = R.anim.nav_default_pop_exit_anim
    }
}