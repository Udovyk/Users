package com.udovyk.users.ui.userslist.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.udovyk.users.network.ApiManager
import com.udovyk.users.network.model.ResultsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ItemDataSource @Inject constructor(private val apiManager: ApiManager) : PageKeyedDataSource<Int, ResultsItem>() {
    companion object {
        const val PAGE_SIZE = 20
        const val FIRST_PAGE = 1
    }

    private val disposable = CompositeDisposable()
    private val progressLiveData = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ResultsItem>) {
        progressLiveData.postValue(true)
        disposable.add(
            apiManager.getUsers(FIRST_PAGE, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { responseResponse ->
                        if (responseResponse.body() != null) {

                            callback.onResult(responseResponse.body()!!.results!!, null, FIRST_PAGE + 1)
                        }
                    }, {}

                ))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsItem>) {
        progressLiveData.postValue(true)
        disposable.add(
            apiManager.getUsers(FIRST_PAGE, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { responseResponse ->
                        if (responseResponse.body() != null) {
                            val adjacentKey = (if (params.key > 1) params.key - 1 else null)!!.toInt()
                            callback.onResult(responseResponse.body()!!.results!!, adjacentKey)
                        }
                    }, {}
                ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsItem>) {
        disposable.add(
            apiManager.getUsers(FIRST_PAGE, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { responseResponse ->
                        if (responseResponse.body() != null) {
                            progressLiveData.postValue(false)
                            val key = params.key + 1
                            callback.onResult(responseResponse.body()!!.results!!, key)
                        }
                    }, {}
                ))
    }

    fun getProgressLiveData(): MutableLiveData<Boolean> {
        return progressLiveData
    }


}