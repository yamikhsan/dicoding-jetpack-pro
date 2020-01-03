package com.studio.yami.ajpfinal.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM Favorite WHERE category = :category")
    fun getFavoriteAll(category: String): DataSource.Factory<Int, Favorite>

    @Query("SELECT id FROM Favorite WHERE id = :id")
    fun getFavoriteById(id: Int): LiveData<Int?>

    @Insert
    fun insertFavorite(favorite: Favorite)

    @Delete
    fun deleteFavorite(favorite: Favorite)

}