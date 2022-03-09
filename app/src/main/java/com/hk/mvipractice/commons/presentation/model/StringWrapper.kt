package com.hk.mvipractice.commons.presentation.model

import android.content.Context
import androidx.annotation.StringRes

/**
 * Used to wrap error messages and updates in a locale friendly way
 * so the UI just calls [getString] without worrying about handling messages.
 *
 * @property stringId mostly provided from error handlers like [ExceptionHandler] to handle errors
 * with UI friendly way and any ( object not holding reference to a context )
 * @property stringMessage mostly used to display the message from the API response. If it wasn't matching the locale
 * then it's a localized API issue or App Localization handling issue while making requests
 * @property multipleResFormatter as this class is used to wrap localized strings from non-Context-accessors
 * ( object not holding reference to a context ) this property is used to wrap strings with formatting
 * i.e. (User name is %s) in a lambda that uses [Context] as the receiver i.e. {fun Context.getString()}
 * to allow for further manipulation while not having an actual reference to the [Context] itself.
 * Like setting logic to be executed later with a [Context] owner.
 */
data class StringWrapper(
    @StringRes private val stringId: Int? = null,
    private val stringMessage: String? = null,
    private val multipleResFormatter: ((Context) -> String)? = null
){
    /**
     * this constructor is to be able to pass the string param right way without naming the param
     * while creating the same object
     */
    constructor(
        stringMessage: String? = null,
    ) : this(null, stringMessage, null)

    /**
     * This is a single method Constructor to pass [multipleResFormatter] as a parameter without
     * passing other members
     * i.e.
     * StringWrapper { getString(R.string.cant_be_empty, getString(R.string.phone_number)) }
     *
     */
    constructor(
            multipleResFormatter: (Context.() -> String)?
    ) : this(null, null, multipleResFormatter)


    fun getString(context: Context): String {
        multipleResFormatter?.let {
            return it.invoke(context)
        }
        stringId?.let {
            return context.getString(it)
        }
        stringMessage?.let {
            return it
        }
        return ""
    }
    operator fun plus(wrapper: StringWrapper): StringWrapper {
        return StringWrapper {
            this@StringWrapper.getString(this).plus(wrapper.getString(this))
        }
    }
}

