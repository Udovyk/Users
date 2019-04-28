package com.udovyk.users.ui.base


import androidx.lifecycle.ViewModel
import com.udovyk.users.network.ApiManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(){
    var disposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var apiManager: ApiManager

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}