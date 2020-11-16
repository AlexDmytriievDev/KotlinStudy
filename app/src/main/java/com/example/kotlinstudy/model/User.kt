package com.example.kotlinstudy.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinstudy.utils.Constants
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = Constants.USERS)
data class User(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var email: String = Constants.CHAR.EMPTY,
    var firstName: String = Constants.CHAR.EMPTY,
    var lastName: String = Constants.CHAR.EMPTY,
    var avatarUrl: String = Constants.CHAR.EMPTY,
    var password: String = Constants.CHAR.EMPTY,
    var isSignIn: Boolean = false
) : Parcelable