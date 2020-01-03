package com.studio.yami.ajpfinal.data.server.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.studio.yami.ajpfinal.data.local.Favorite

class ListDataSourceFactory(
    private val listDataSource: ListDataSource
) :
    DataSource.Factory<Int, Favorite>() {

    val networkState = MutableLiveData<ListDataSource>()

    override fun create(): DataSource<Int, Favorite> {
        networkState.postValue(listDataSource)
        return listDataSource
    }
}