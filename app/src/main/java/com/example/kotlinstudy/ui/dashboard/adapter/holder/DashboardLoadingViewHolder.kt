package com.example.kotlinstudy.ui.dashboard.adapter.holder

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.databinding.ViewHolderDashboardLoadingBinding

class DashboardLoadingViewHolder(
    private val binding: ViewHolderDashboardLoadingBinding,
    listener: View.OnClickListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.loadingBtn.setOnClickListener(listener)
    }

    fun onBind(loadState: LoadState) {
        binding.apply {
            if (loadState is LoadState.Error)
                loadingMessage.text = loadState.error.localizedMessage
            loadingView.isVisible = loadState is LoadState.Loading
            loadingMessage.isVisible = loadState !is LoadState.Loading
            loadingBtn.isVisible = loadState !is LoadState.Loading
        }
    }
}