package com.hk.mvipractice.contracts

import android.content.Context
import com.hk.mvipractice.commons.domain.exceptions.ErrorCodes
import com.hk.mvipractice.commons.presentation.model.StringWrapper

/**
 * Created by hassankhamis on 27,February,2022
 */
class BaseContract {

    // Events that user performed
    sealed class BaseEvent {
        object OnRetryDataClicked : BaseEvent()
    }

    // View State that related to Most screens
    sealed class BaseState {
        object Idle: BaseState()
        object ShimmerLoading: BaseState()
        object Loading: BaseState()
        data class Success(val noData: Boolean): BaseState()
        data class ErrorState(val err: AsyncError): BaseState()

    }

    // Side effects

    sealed class BaseEffect {

        data class ErrorDialog(val err: AsyncError) : BaseEffect()
        object SuccessDialog : BaseEffect()

    }

    sealed class AsyncError {
        data class MessageCodeError(val message: StringWrapper, val code: ErrorCodes? = null) : AsyncError() {
            fun getErrorMessage(context: Context): String {
                return message.getString(context)
            }
        }
    }
}