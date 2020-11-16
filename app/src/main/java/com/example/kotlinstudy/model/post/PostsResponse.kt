package com.example.kotlinstudy.model.post

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostsResponse(val data: Listing?) : Parcelable