package com.studio.yami.ajpfinal.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.data.server.Film
import com.studio.yami.ajpfinal.data.server.NetworkState
import com.studio.yami.ajpfinal.dummydata.dummyFavorite
import com.studio.yami.ajpfinal.dummydata.dummyFilmResult
import com.studio.yami.ajpfinal.untils.PagedListUtil
import com.studio.yami.ajpfinal.untils.mock
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FilmRepositoryTest {

    private val id = 1

    private val serverRepository: ServerRepository = mock()
    private val localRepository: LocalRepository = mock()
    private val filmRepository = FilmRepository(serverRepository, localRepository)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getNetworkState() {
        val observer: Observer<NetworkState> = mock()

        val dummyNetworkState = MutableLiveData<NetworkState>()
        val network = NetworkState.LOADED
        dummyNetworkState.postValue(network)

        Mockito.`when`(serverRepository.networkState()).thenReturn(dummyNetworkState)
        filmRepository.networkState().observeForever(observer)
        Mockito.verify(observer).onChanged(network)
    }

    @Test
    fun getServerList() {
        val observer: Observer<PagedList<Favorite>> = mock()

        val dummyFavoriteAll = MutableLiveData<PagedList<Favorite>>()
        val pagedList = PagedListUtil.mockPagedList(dummyFavorite())
        dummyFavoriteAll.postValue(pagedList)

        Mockito.`when`(serverRepository.getFilmList()).thenReturn(dummyFavoriteAll)
        filmRepository.getServerList().observeForever(observer)
        Mockito.verify(observer).onChanged(pagedList)
    }

    @Test
    fun getServerDetail() {
        val observer: Observer<Film.Result?> = mock()

        val dummyDetail = MutableLiveData<Film.Result>()
        val detail = dummyFilmResult()
        dummyDetail.postValue(detail)

        Mockito.`when`(serverRepository.getFilmDetail(id)).thenReturn(dummyDetail)
        filmRepository.getServerDetail(id).observeForever(observer)
        Mockito.verify(observer).onChanged(detail)
    }

    @Test
    fun getFavoriteAll() {
        val observer: Observer<PagedList<Favorite>> = mock()

        val dummyFavoriteAll = MutableLiveData<PagedList<Favorite>>()
        val pagedList = PagedListUtil.mockPagedList(dummyFavorite())
        dummyFavoriteAll.postValue(pagedList)

        Mockito.`when`(localRepository.getFavoriteAll()).thenReturn(dummyFavoriteAll)
        filmRepository.getFavoriteAll().observeForever(observer)
        Mockito.verify(observer).onChanged(pagedList)
    }

    @Test
    fun getFavoriteById() {
        val observer: Observer<Int?> = mock()

        val dummyFavoriteById = MutableLiveData<Int?>()
        dummyFavoriteById.postValue(id)

        Mockito.`when`(localRepository.getFavoriteById(id)).thenReturn(dummyFavoriteById)
        filmRepository.getFavoriteById(id).observeForever(observer)
        Mockito.verify(observer).onChanged(id)
    }
}