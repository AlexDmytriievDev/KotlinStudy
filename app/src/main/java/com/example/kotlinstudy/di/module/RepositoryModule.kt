package com.example.kotlinstudy.di.module

import com.example.kotlinstudy.database.AppDatabase
import com.example.kotlinstudy.database.DataBaseRepository
import com.example.kotlinstudy.database.DataBaseRepositoryImpl
import com.example.kotlinstudy.network.NetworkRepository
import com.example.kotlinstudy.network.NetworkRepositoryImpl
import com.example.kotlinstudy.network.RestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataBaseRepository(appDatabase: AppDatabase): DataBaseRepository {
        return DataBaseRepositoryImpl(appDatabase)
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(restApi: RestApi): NetworkRepository {
        return NetworkRepositoryImpl(restApi)
    }
}