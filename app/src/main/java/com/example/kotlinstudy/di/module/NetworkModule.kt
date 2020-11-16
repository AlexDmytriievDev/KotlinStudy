package com.example.kotlinstudy.di.module

import android.content.Context
import com.example.kotlinstudy.network.PostPagingSource
import com.example.kotlinstudy.network.NetworkRepository
import com.example.kotlinstudy.network.NetworkRepositoryImpl
import com.example.kotlinstudy.network.RestApi
import com.example.kotlinstudy.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(restApi: RestApi): NetworkRepository {
        return NetworkRepositoryImpl(PostPagingSource(restApi))
    }

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): RestApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(Constants.NETWORK.TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.NETWORK.TIMEOUT, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, Constants.NETWORK.CACHE_SIZE))
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .build()
    }
}