package com.example.kotlinstudy.model

import android.os.Parcelable
import com.example.kotlinstudy.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Award(
    @SerializedName(Constants.ICON_URL)
    val url: String?,
) : Parcelable