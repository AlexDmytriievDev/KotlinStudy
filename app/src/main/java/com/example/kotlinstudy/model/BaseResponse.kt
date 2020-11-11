package com.example.kotlinstudy.model

import android.os.Parcelable
import androidx.annotation.IntDef
import com.example.kotlinstudy.model.BaseResponse.Code.Companion.ERROR
import com.example.kotlinstudy.model.BaseResponse.Code.Companion.SUCCESS
import com.example.kotlinstudy.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseResponse(
    @SerializedName(Constants.CODE) @Code val code: Int,
    @SerializedName(Constants.MESSAGE) val message: String
) : Parcelable {

    fun isSuccess() = code == SUCCESS

    @IntDef(SUCCESS, ERROR)
    @kotlin.annotation.Retention
    annotation class Code {
        companion object {
            const val SUCCESS = 0
            const val ERROR = 1
        }
    }
}