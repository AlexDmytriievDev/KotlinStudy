package com.example.kotlinstudy.utils

import android.widget.ImageView
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.setCircleImage(url: String) {
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun LoadState.isNotLoading() = this is LoadState.NotLoading
fun LoadState.isLoading() = this is LoadState.Loading
fun LoadState.isError() = this is LoadState.Error
fun LoadState.errorMessage() =
    if (this is LoadState.Error) this.error.localizedMessage else Constants.CHAR.EMPTY