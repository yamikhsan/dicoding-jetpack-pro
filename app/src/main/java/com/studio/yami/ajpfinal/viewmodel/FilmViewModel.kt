package com.studio.yami.ajpfinal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.data.server.Film
import com.studio.yami.ajpfinal.repository.FilmRepository

class FilmViewModel(private val repo: FilmRepository): ViewModel() {


    fun getServerList(): LiveData<PagedList<Favorite>> = repo.getServerList()

    fun networkState() = repo.networkState()

    fun getServerDetail(id: Int): LiveData<Film.Result?> = repo.getServerDetail(id)

    fun getFavoriteAll() = repo.getFavoriteAll()

    fun getFavoriteById(id: Int) = repo.getFavoriteById(id)

    fun insertFavorite(favorite: Favorite){
        repo.insertFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite){
        repo.deleteFavorite(favorite)
    }

}