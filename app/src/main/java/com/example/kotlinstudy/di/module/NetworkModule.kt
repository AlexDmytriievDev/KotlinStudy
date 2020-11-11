package com.example.kotlinstudy.di.module

import android.content.Context
import com.example.kotlinstudy.di.qualifier.ErrorsInterceptor
import com.example.kotlinstudy.di.qualifier.HeaderInterceptor
import com.example.kotlinstudy.di.qualifier.LoggingInterceptor
import com.example.kotlinstudy.di.qualifier.NetworkInterceptor
import com.example.kotlinstudy.network.RestApi
import com.example.kotlinstudy.utils.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Field
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApi(gson: Gson, okHttpClient: OkHttpClient): RestApi {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.API.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(RestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @NetworkInterceptor networkInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor,
        @HeaderInterceptor headerInterceptor: Interceptor,
        @ErrorsInterceptor errorsInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient? {
        return OkHttpClient.Builder()
            .readTimeout(Constants.NETWORK.TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.NETWORK.TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .cache(cache)
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(errorsInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingStrategy { field: Field ->
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
                    .translateName(field).substring(2).toLowerCase()
            }
            .setDateFormat(Constants.NETWORK.GSON_DATE_FORMAT)
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .serializeNulls()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, Constants.NETWORK.CACHE_SIZE)
    }

}