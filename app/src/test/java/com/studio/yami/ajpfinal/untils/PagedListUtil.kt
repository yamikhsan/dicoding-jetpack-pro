package com.studio.yami.ajpfinal.untils

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.stubbing.Answer


object PagedListUtil {

    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList: PagedList<T> = mock()
        val answer: Answer<T> = Answer { invocation ->
            val index = invocation.arguments[0] as Int
            list[index]
        }

        `when`<T>(pagedList[anyInt()]).thenAnswer(answer)
        `when`<Int>(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}