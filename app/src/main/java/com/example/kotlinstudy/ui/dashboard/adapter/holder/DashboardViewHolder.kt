package com.example.kotlinstudy.ui.dashboard.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.databinding.ViewHolderDashboardPostBinding
import com.example.kotlinstudy.model.post.Post
import com.example.kotlinstudy.ui.dashboard.adapter.DashboardAdapter

class DashboardViewHolder(
    private val binding: ViewHolderDashboardPostBinding,
    listener: DashboardAdapter.OnPostClickListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.postCard.setOnClickListener {
            listener.onPostClick(binding.post)
        }
    }

    fun onBind(post: Post?) {
        binding.post = post
    }
}