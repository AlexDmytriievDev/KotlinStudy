package com.example.kotlinstudy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinstudy.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}