package com.studio.yami.ajpfinal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.data.server.Film
import com.studio.yami.ajpfinal.data.server.datasource.DetailDataSource
import com.studio.yami.ajpfinal.data.server.datasource.ListDataSourceFactory

class ServerRepository(
    private val listSourceFactory: ListDataSourceFactory,
    private val detailSourceFilm: DetailDataSource
) {
    fun networkState() = Transformations.switchMap(listSourceFactory.networkState){
        it.networkState
    }

    fun getFilmList(): LiveData<PagedList<Favorite>> {

        val pagedConfig: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(20).build()

        return LivePagedListBuilder(listSourceFactory, pagedConfig)
            .build()
    }

    fun getFilmDetail(id: Int): LiveData<Film.Result?> = detailSourceFilm.getFilmDetail(id)

}