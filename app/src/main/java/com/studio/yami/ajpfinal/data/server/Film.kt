package com.studio.yami.ajpfinal.data.server

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

object Film{

    data class Discover(val results: List<Result>)
    data class Result(
        val id: Int?,
        val poster_path: String?,
        val genres: List<Genres>?,
        val overview: String?,
        val status: String?,
        val vote_average: Double?,
        val runtime: Int?,
        val episode_run_time: List<Int?>?,
        val original_language: String?,
        var category: String?,
        @SerializedName(value = "title", alternate = ["name"]) val title: String?,
        @SerializedName(value = "release_date", alternate = ["first_air_date"]) val release_date: String?
    ){
        fun getVoteAverage(): String = vote_average.toString()

        fun getDuration(): String? {
            val none = "-"
            val duration = when(category){
                "movie" -> runtime?.toString() ?: none
                "tv" -> if(episode_run_time?.size!! > 0) episode_run_time[0].toString() else none
                else -> none
            }
            return "$duration m"
        }

        @SuppressLint("SimpleDateFormat")
        fun getDate(): String? {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(release_date)
            val format = SimpleDateFormat("dd MMMM yyyy")
            return format.format(date)
        }

        fun getGenre(): String{
            val builder = StringBuilder()
            genres?.forEachIndexed { i, v ->
                builder.append(v.name)
                if(i != genres.size - 1){
                    builder.append(", ")
                }
            }
            return builder.toString()
        }

    }
    data class Genres(
        val name: String
    )

}