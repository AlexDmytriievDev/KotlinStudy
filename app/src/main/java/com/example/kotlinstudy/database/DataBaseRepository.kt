package com.example.kotlinstudy.database

import com.example.kotlinstudy.model.User
import io.reactivex.Single

interface DataBaseRepository {

    fun addUser(user: User): Single<Boolean>

    fun getSignInUser(): Single<User>

    fun getAll(): Single<List<User>>

    fun updateUser(user: User): Single<Boolean>

    fun logoutUser(): Single<Boolean>

    fun deleteUser(user: User): Single<Boolean>

    fun clear(): Single<Boolean>
}