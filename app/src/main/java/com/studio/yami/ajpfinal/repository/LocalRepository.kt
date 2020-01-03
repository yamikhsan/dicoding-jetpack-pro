package com.studio.yami.ajpfinal.repository

import androidx.paging.LivePagedListBuilder
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.data.local.FavoriteDao
import java.util.concurrent.Executors

class LocalRepository(private val dao: FavoriteDao, private val category: String){

    private val executorService = Executors.newSingleThreadExecutor()

    fun getFavoriteAll() = LivePagedListBuilder(dao.getFavoriteAll(category), 10).build()

    fun getFavoriteById(id: Int) = dao.getFavoriteById(id)

    fun insertFavorite(favorite: Favorite){
        executorService.execute {
            dao.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite){
        executorService.execute {
            dao.deleteFavorite(favorite)
        }
    }

}