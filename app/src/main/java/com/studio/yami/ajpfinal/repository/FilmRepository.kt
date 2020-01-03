package com.studio.yami.ajpfinal.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.data.server.Film

class FilmRepository(private val server: ServerRepository, private val local: LocalRepository) {

    fun getServerList(): LiveData<PagedList<Favorite>> = server.getFilmList()

    fun networkState() = server.networkState()

    fun getServerDetail(id: Int): LiveData<Film.Result?> = server.getFilmDetail(id)

    fun getFavoriteAll() = local.getFavoriteAll()

    fun getFavoriteById(id: Int) = local.getFavoriteById(id)

    fun insertFavorite(favorite: Favorite){
        local.insertFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite){
        local.deleteFavorite(favorite)
    }

}