package com.udovyk.users.ui.userslist

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.udovyk.users.network.model.ResultsItem
import com.udovyk.users.ui.base.BaseViewModel
import com.udovyk.users.ui.userslist.paging.ItemDataSource
import com.udovyk.users.ui.userslist.paging.ItemDataSourceFactory
import javax.inject.Inject

class UsersListViewModel @Inject constructor() : BaseViewModel() {

    lateinit var itemPagedList: LiveData<PagedList<ResultsItem>>
    lateinit var liveDataSource: LiveData<PageKeyedDataSource<Int, ResultsItem>>
    lateinit var progressLiveData: LiveData<Boolean>
    var itemDataSourceFactory: ItemDataSourceFactory? = null


    fun getUsers() {
        itemDataSourceFactory = ItemDataSourceFactory(apiManager)
        progressLiveData = itemDataSourceFactory!!.itemDataSource.getProgressLiveData()
        liveDataSource = itemDataSourceFactory!!.itemLiveDataSource


        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ItemDataSource.PAGE_SIZE).build()
        itemPagedList = LivePagedListBuilder(itemDataSourceFactory!!, pagedListConfig).build()
    }

    override fun onCleared() {
        super.onCleared()
        itemDataSourceFactory!!.itemDataSource.clear()
    }
}
