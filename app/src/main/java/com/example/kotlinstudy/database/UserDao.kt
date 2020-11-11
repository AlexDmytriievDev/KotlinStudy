package com.example.kotlinstudy.database

import androidx.room.*
import com.example.kotlinstudy.model.User
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User): Completable

    @Query("SELECT * FROM users")
    fun getUsers(): Maybe<List<User>>

    @Query("SELECT * FROM users WHERE isSignIn = 1")
    fun getSignInUser(): Maybe<User>

    @Update
    fun updateUser(user: User): Completable

    @Delete
    fun deleteUser(user: User): Completable

    @Query("DELETE FROM users")
    fun clear(): Completable
}