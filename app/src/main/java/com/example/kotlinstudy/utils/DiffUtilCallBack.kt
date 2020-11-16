package com.example.kotlinstudy.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlinstudy.model.post.Post

class DiffUtilCallBack : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.name == newItem.name
                && oldItem.score == newItem.score
                && oldItem.commentCount == newItem.commentCount
    }
}