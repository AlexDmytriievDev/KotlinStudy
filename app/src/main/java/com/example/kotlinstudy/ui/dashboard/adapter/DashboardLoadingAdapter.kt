package com.example.kotlinstudy.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.kotlinstudy.databinding.ViewHolderDashboardLoadingBinding
import com.example.kotlinstudy.ui.dashboard.adapter.holder.DashboardLoadingViewHolder

class DashboardLoadingAdapter(private val listener: View.OnClickListener) :
    LoadStateAdapter<DashboardLoadingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): DashboardLoadingViewHolder {
        return DashboardLoadingViewHolder(
            ViewHolderDashboardLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: DashboardLoadingViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }
}