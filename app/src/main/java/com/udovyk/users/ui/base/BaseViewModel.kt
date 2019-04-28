package com.udovyk.users.ui.base


import androidx.lifecycle.ViewModel
import com.udovyk.users.network.ApiManager
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(){
    @Inject
    lateinit var apiManager: ApiManager
}