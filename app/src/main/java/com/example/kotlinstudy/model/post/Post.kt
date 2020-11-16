package com.example.kotlinstudy.model.post

import android.os.Parcelable
import com.example.kotlinstudy.model.Award
import com.example.kotlinstudy.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: String = "",
    val name: String = "",
    val author: String = "",
    val title: String = "",
    val score: Int = 0,
    @SerializedName(Constants.NUM_COMMENTS)
    val commentCount: Int = 0,
    @SerializedName(Constants.ALL_AWARDINGS)
    val awards: List<Award> = listOf()
) : Parcelable {

    fun getImage(): String {
        for (award in awards) {
            if (!award.url.isNullOrBlank()) {
                return award.url
            }
        }
        return ""
    }
}