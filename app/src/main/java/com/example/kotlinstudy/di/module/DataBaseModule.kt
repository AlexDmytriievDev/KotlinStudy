package com.example.kotlinstudy.di.module

import android.content.Context
import androidx.room.Room
import com.example.kotlinstudy.database.AppDatabase
import com.example.kotlinstudy.database.DataBaseRepository
import com.example.kotlinstudy.database.DataBaseRepositoryImpl
import com.example.kotlinstudy.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBaseRepository(appDatabase: AppDatabase): DataBaseRepository {
        return DataBaseRepositoryImpl(appDatabase)
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, Constants.DB.NAME
        ).build()
    }
}