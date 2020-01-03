package com.studio.yami.ajpfinal.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.data.server.Film
import com.studio.yami.ajpfinal.data.server.NetworkState
import com.studio.yami.ajpfinal.dummydata.dummyFavorite
import com.studio.yami.ajpfinal.dummydata.dummyFilmResult
import com.studio.yami.ajpfinal.repository.FilmRepository
import com.studio.yami.ajpfinal.untils.PagedListUtil
import com.studio.yami.ajpfinal.untils.mock
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FilmViewModelTest {
    private val id = 1

    private val filmRepository: FilmRepository = mock()
    private val filmViewModel = FilmViewModel(filmRepository)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun networkState() {
        val observer: Observer<NetworkState> = mock()

        val dummyNetworkState = MutableLiveData<NetworkState>()
        val network = NetworkState.LOADED
        dummyNetworkState.postValue(network)

        Mockito.`when`(filmRepository.networkState()).thenReturn(dummyNetworkState)
        filmViewModel.networkState().observeForever(observer)
        Mockito.verify(observer).onChanged(network)
    }

    @Test
    fun getServerList() {
        val observer: Observer<PagedList<Favorite>> = mock()

        val dummyFavoriteAll = MutableLiveData<PagedList<Favorite>>()
        val pagedList = PagedListUtil.mockPagedList(dummyFavorite())
        dummyFavoriteAll.postValue(pagedList)

        Mockito.`when`(filmRepository.getServerList()).thenReturn(dummyFavoriteAll)
        filmViewModel.getServerList().observeForever(observer)
        Mockito.verify(observer).onChanged(pagedList)
    }

    @Test
    fun getServerDetail() {
        val observer: Observer<Film.Result?> = mock()

        val dummyDetail = MutableLiveData<Film.Result>()
        val detail = dummyFilmResult()
        dummyDetail.postValue(detail)

        Mockito.`when`(filmRepository.getServerDetail(id)).thenReturn(dummyDetail)
        filmViewModel.getServerDetail(id).observeForever(observer)
        Mockito.verify(observer).onChanged(detail)
    }

    @Test
    fun getFavoriteAll() {
        val observer: Observer<PagedList<Favorite>> = mock()

        val dummyFavoriteAll = MutableLiveData<PagedList<Favorite>>()
        val pagedList = PagedListUtil.mockPagedList(dummyFavorite())
        dummyFavoriteAll.postValue(pagedList)

        Mockito.`when`(filmRepository.getFavoriteAll()).thenReturn(dummyFavoriteAll)
        filmViewModel.getFavoriteAll().observeForever(observer)
        Mockito.verify(observer).onChanged(pagedList)
    }

    @Test
    fun getFavoriteById() {
        val observer: Observer<Int?> = mock()

        val dummyFavoriteById = MutableLiveData<Int?>()
        dummyFavoriteById.postValue(id)

        Mockito.`when`(filmRepository.getFavoriteById(id)).thenReturn(dummyFavoriteById)
        filmViewModel.getFavoriteById(id).observeForever(observer)
        Mockito.verify(observer).onChanged(id)
    }
}