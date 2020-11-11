package com.example.kotlinstudy.di.module

import android.content.Context
import android.text.TextUtils
import com.example.kotlinstudy.BuildConfig
import com.example.kotlinstudy.di.qualifier.ErrorsInterceptor
import com.example.kotlinstudy.di.qualifier.HeaderInterceptor
import com.example.kotlinstudy.di.qualifier.LoggingInterceptor
import com.example.kotlinstudy.di.qualifier.NetworkInterceptor
import com.example.kotlinstudy.model.BaseResponse
import com.example.kotlinstudy.network.exceptions.InternalException
import com.example.kotlinstudy.network.exceptions.NoInternetException
import com.example.kotlinstudy.network.exceptions.ResponseException
import com.example.kotlinstudy.utils.NetworkUtils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InterceptorsModule {

    @Provides
    @Singleton
    @NetworkInterceptor
    fun provideNetworkInterceptor(@ApplicationContext appContext: Context): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            if (NetworkUtils.isNetworkAvailable(appContext)) {
                chain.proceed(chain.request().newBuilder().build())
            } else throw NoInternetException()
        }
    }

    @Provides
    @Singleton
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS
            else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    @Singleton
    @HeaderInterceptor
    fun provideHeaderInterceptor(): Interceptor? {
        return Interceptor { chain: Interceptor.Chain ->
            chain.proceed(chain.request().newBuilder().build())
        }
    }

    @Provides
    @Singleton
    @ErrorsInterceptor
    fun provideErrorsInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            val responseBody = response.body ?: throw ResponseException("Empty response")
            val contentType = responseBody.contentType()
            val bodyString = responseBody.string()
            responseBody.close()
            if (!TextUtils.isEmpty(bodyString)) {
                val responseModel: BaseResponse
                try {
                    responseModel = Gson().fromJson(bodyString, BaseResponse::class.java)
                } catch (e: IllegalStateException) {
                    Timber.e(e)
                    throw ResponseException("Invalid server response")
                }
                if (!responseModel.isSuccess()) {
                    Timber.e("Error url: %s", request.url)
                    Timber.e("Error response: %s", responseModel.toString())
                    throw InternalException(responseModel)
                }
            } else {
                throw ResponseException("Invalid server response")
            }
            val body = bodyString.toResponseBody(contentType)
            response.newBuilder().body(body).build()
        }
    }
}