package com.studio.yami.ajpfinal.data.server.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studio.yami.ajpfinal.data.server.Film
import com.studio.yami.ajpfinal.data.server.rest.ApiService
import com.studio.yami.ajpfinal.idleresource.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailDataSource(
    private val apiService: ApiService,
    private val key: String,
    private val category: String
) {

    fun getFilmDetail(id: Int): LiveData<Film.Result?> {
        EspressoIdlingResource.increment()
        val filmDetail = MutableLiveData<Film.Result>()
        apiService.getDetail(category, id, key).enqueue(object : Callback<Film.Result> {
            override fun onResponse(call: Call<Film.Result>, response: Response<Film.Result>) {
                if (response.code() == 200) {
                    filmDetail.postValue(response.body().apply {
                        this?.category = category
                    })
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Film.Result>, error: Throwable) {
                error.printStackTrace()
                EspressoIdlingResource.decrement()
            }
        })
        return filmDetail
    }

}