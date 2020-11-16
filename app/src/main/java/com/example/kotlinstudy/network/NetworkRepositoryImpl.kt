package com.example.kotlinstudy.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.kotlinstudy.model.post.Post
import com.example.kotlinstudy.utils.Constants
import io.reactivex.Flowable

class NetworkRepositoryImpl(private val source: PostPagingSource) : NetworkRepository {

    override fun getPosts(): Flowable<PagingData<Post>> {
        return Pager(
            PagingConfig(
                enablePlaceholders = false,
                pageSize = Constants.NUM.POST_DEF_LOAD_SIZE,
                prefetchDistance = Constants.NUM.POST_PREFETCH_DISTANCE
            ),
            pagingSourceFactory = { source }
        ).flowable
    }
}