package com.example.adabank.feature_ababank.data.util

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

object Util {

    fun Context.getActivity(): ComponentActivity? = when (this) {
        is ComponentActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> null
    }
}