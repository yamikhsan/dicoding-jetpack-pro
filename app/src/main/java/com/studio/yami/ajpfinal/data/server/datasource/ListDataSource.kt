package com.studio.yami.ajpfinal.data.server.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.data.server.Film
import com.studio.yami.ajpfinal.data.server.NetworkState
import com.studio.yami.ajpfinal.data.server.rest.ApiService
import com.studio.yami.ajpfinal.idleresource.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListDataSource(
    private val apiService: ApiService,
    private val key: String,
    private val category: String
) :
    PageKeyedDataSource<Int, Favorite>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Favorite>) {

        networkState.postValue(NetworkState.LOADING)
        EspressoIdlingResource.increment()
        apiService.getDiscover(category, 1, key).enqueue(object : Callback<Film.Discover> {
            override fun onResponse(call: Call<Film.Discover>, response: Response<Film.Discover>) {
                if (response.code() == 200) {
                    val list = mutableListOf<Favorite?>()
                    response.body()?.results?.forEach {
                        list.add(
                            Favorite(
                                it.id,
                                it.poster_path,
                                it.title,
                                it.getVoteAverage(),
                                it.original_language,
                                category
                            )
                        )
                    }

                    networkState.postValue(NetworkState.LOADED)

                    callback.onResult(list, null, 2)
                } else {
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED, response.message()))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Film.Discover>, error: Throwable) {
                error.printStackTrace()
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, error.message!!))
                EspressoIdlingResource.decrement()
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Favorite>) {

        networkState.postValue(NetworkState.LOADING)
        EspressoIdlingResource.increment()
        apiService.getDiscover(category, params.key, key).enqueue(object : Callback<Film.Discover> {
            override fun onResponse(call: Call<Film.Discover>, response: Response<Film.Discover>) {
                if (response.code() == 200) {

                    val list = mutableListOf<Favorite?>()
                    response.body()?.results?.forEach {
                        list.add(
                            Favorite(
                                it.id,
                                it.poster_path,
                                it.title,
                                it.getVoteAverage(),
                                it.original_language,
                                category
                            )
                        )
                    }

                    networkState.postValue(NetworkState.LOADED)

                    callback.onResult(list, params.key + 1)
                } else {
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED, response.message()))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Film.Discover>, error: Throwable) {
                error.printStackTrace()
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, error.message!!))
                EspressoIdlingResource.decrement()
            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Favorite>) {

    }
}


