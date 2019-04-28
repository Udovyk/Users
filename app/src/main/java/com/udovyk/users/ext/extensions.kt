package com.udovyk.users.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> ViewModelProvider.getViewModelOfType(): T =
        get(T::class.java)