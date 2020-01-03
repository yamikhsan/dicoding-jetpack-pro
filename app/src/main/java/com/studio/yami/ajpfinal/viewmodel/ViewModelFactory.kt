package com.studio.yami.ajpfinal.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studio.yami.ajpfinal.BuildConfig
import com.studio.yami.ajpfinal.data.local.FavoriteDatabase
import com.studio.yami.ajpfinal.data.server.datasource.DetailDataSource
import com.studio.yami.ajpfinal.data.server.datasource.ListDataSource
import com.studio.yami.ajpfinal.data.server.datasource.ListDataSourceFactory
import com.studio.yami.ajpfinal.data.server.rest.ApiClient
import com.studio.yami.ajpfinal.repository.FilmRepository
import com.studio.yami.ajpfinal.repository.LocalRepository
import com.studio.yami.ajpfinal.repository.ServerRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application, category: String):
    ViewModelProvider.Factory {

    private val key = BuildConfig.ApiKey
    private val apiService = ApiClient().getService()

    private val listDataSource = ListDataSource(apiService, key, category)
    private val listDataSourceFactory = ListDataSourceFactory(listDataSource)
    private val detailDataSource = DetailDataSource(apiService, key, category)

    private val dao = FavoriteDatabase.getDatabase(application).favoriteDao()

    private val server = ServerRepository(listDataSourceFactory, detailDataSource)
    private val local = LocalRepository(dao, category)
    private val film = FilmRepository(server, local)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FilmViewModel::class.java)) {

            return FilmViewModel(film) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}