package com.udovyk.users.ui.userslist.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.udovyk.users.network.ApiManager
import com.udovyk.users.network.model.ResultsItem

class ItemDataSourceFactory (apiManager: ApiManager) : DataSource.Factory<Int, ResultsItem>() {

    val itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, ResultsItem>> = MutableLiveData()

    var itemDataSource: ItemDataSource = ItemDataSource(apiManager)

    override
    fun create(): DataSource<Int, ResultsItem> {
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }


}