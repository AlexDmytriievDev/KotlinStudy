package com.example.kotlinstudy.database

import com.example.kotlinstudy.model.User
import io.reactivex.Single

class DataBaseRepositoryImpl(private val database: AppDatabase) : DataBaseRepository {

    override fun addUser(user: User): Single<Boolean> {
        return database.getUserDao()
            .addUser(user)
            .toSingle { true }
            .onErrorReturnItem(false)
    }

    override fun getSignInUser(): Single<User> {
        return database.getUserDao()
            .getSignInUser()
            .defaultIfEmpty(User())
            .toSingle()
    }

    override fun getAll(): Single<List<User>> {
        return database.getUserDao()
            .getUsers()
            .defaultIfEmpty(mutableListOf())
            .toSingle()
    }

    override fun updateUser(user: User): Single<Boolean> {
        return database.getUserDao()
            .updateUser(user)
            .toSingle { true }
            .onErrorReturnItem(false)
    }

    override fun logoutUser(): Single<Boolean> {
        return getSignInUser().flatMap { user: User ->
            if (user.isSignIn) updateUser(user)
            else Single.just(false)
        }.onErrorReturnItem(false)
    }

    override fun deleteUser(user: User): Single<Boolean> {
        return database.getUserDao()
            .deleteUser(user)
            .toSingle { true }
            .onErrorReturnItem(false)
    }

    override fun clear(): Single<Boolean> {
        return database.getUserDao()
            .clear()
            .toSingle { true }
            .onErrorReturnItem(false)
    }

}