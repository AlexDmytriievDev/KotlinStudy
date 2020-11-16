package com.example.kotlinstudy.model.post

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Listing(
    val after: String?,
    val before: String?,
    val children: List<Container>?
) : Parcelable