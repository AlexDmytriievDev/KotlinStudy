package com.example.kotlinstudy.network

import com.example.kotlinstudy.model.post.PostsResponse
import com.example.kotlinstudy.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET(Constants.API.GET_POSTS)
    suspend fun getPosts(
        @Query(Constants.LIMIT) loadSize: Int = Constants.NUM.POST_DEF_LOAD_SIZE,
        @Query(Constants.AFTER) after: String? = null,
        @Query(Constants.BEFORE) before: String? = null
    ): Response<PostsResponse>
}