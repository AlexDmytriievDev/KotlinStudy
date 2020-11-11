package com.example.kotlinstudy.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.kotlinstudy.utils.Constants
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = Constants.USERS)
data class User(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var avatarUrl: String = "",
    var password: String = "",
    var isSignIn: Boolean = false
) : Parcelable {

    @Ignore
    constructor(email: String, password: String) : this(
        email = email,
        password = password,
        isSignIn = true
    )
}