package com.example.kotlinstudy.network

import androidx.paging.PagingData
import com.example.kotlinstudy.model.post.Post
import io.reactivex.Flowable

interface NetworkRepository {

    fun getPosts(): Flowable<PagingData<Post>>
}