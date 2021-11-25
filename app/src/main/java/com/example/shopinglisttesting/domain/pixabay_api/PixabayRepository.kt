package com.example.shopinglisttesting.domain.pixabay_api

import androidx.paging.liveData
import com.example.shopinglisttesting.data.model.pixabay.Hit
import com.example.shopinglisttesting.data.model.pixabay.PixabayPagingSource


import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PixabayRepository @Inject constructor(
    private val pixabayApi: PixabayApi
) {

    fun getSearchResultStream(query: String): LiveData<PagingData<Hit>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PixabayPagingSource(pixabayApi, query) }
        ).liveData
    }
}