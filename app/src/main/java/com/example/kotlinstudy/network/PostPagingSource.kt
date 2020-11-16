package com.example.kotlinstudy.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import com.example.kotlinstudy.model.post.Post

class PostPagingSource(private val api: RestApi) : PagingSource<String, Post>() {

    private val config = MutableLiveData<LoadConfig<String>>()

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Post> {
        return try {
            val response = api.getPosts(
                loadSize = params.loadSize, config.value?.after, config.value?.before
            )

            val listing = response.body()?.data
            val posts = listing?.children?.map { it.data }
            config.value = LoadConfig(listing?.before, listing?.after)

            LoadResult.Page(posts ?: listOf(), listing?.before, listing?.after)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private data class LoadConfig<T>(
        val before: T? = null,
        val after: T? = null,
        val endReached: Boolean = after == null
    )
}