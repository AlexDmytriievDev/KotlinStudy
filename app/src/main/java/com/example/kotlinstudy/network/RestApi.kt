package com.example.kotlinstudy.network

import com.example.kotlinstudy.model.BaseResponse
import com.example.kotlinstudy.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface RestApi {

    @GET(Constants.API.TEST)
    fun getBaseResponse(): Single<BaseResponse>
}