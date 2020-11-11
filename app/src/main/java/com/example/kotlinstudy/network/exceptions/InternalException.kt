package com.example.kotlinstudy.network.exceptions

import android.os.Parcelable
import com.example.kotlinstudy.model.BaseResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InternalException(val response: BaseResponse) : RuntimeException(), Parcelable