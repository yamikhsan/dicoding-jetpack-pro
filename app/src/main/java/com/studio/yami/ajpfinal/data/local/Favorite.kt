package com.studio.yami.ajpfinal.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "poster")
    val poster: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "score")
    val score: String?,

    @ColumnInfo(name = "language")
    val language: String?,

    @ColumnInfo(name = "category")
    val category: String?
): Parcelable