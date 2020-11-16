package com.example.kotlinstudy.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import com.example.kotlinstudy.databinding.ViewHolderDashboardPostBinding
import com.example.kotlinstudy.model.post.Post
import com.example.kotlinstudy.ui.dashboard.adapter.holder.DashboardViewHolder
import com.example.kotlinstudy.utils.DiffUtilCallBack
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardAdapter @Inject constructor() :
    PagingDataAdapter<Post, DashboardViewHolder>(DiffUtilCallBack()) {

    lateinit var listener: OnPostClickListener

    fun setOnClickListener(onClickListener: OnPostClickListener) {
        this.listener = onClickListener;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(
            ViewHolderDashboardPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnPostClickListener {
        fun onPostClick(view: ImageView, post: Post?)
    }
}