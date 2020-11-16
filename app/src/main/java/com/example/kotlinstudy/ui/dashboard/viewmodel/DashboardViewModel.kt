package com.example.kotlinstudy.ui.dashboard.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.kotlinstudy.model.post.Post
import com.example.kotlinstudy.network.NetworkRepository
import com.example.kotlinstudy.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DashboardViewModel @ViewModelInject constructor(
    private val disposables: CompositeDisposable,
    private val networkRepository: NetworkRepository,
) : ViewModel() {

    val data = SingleLiveEvent<PagingData<Post>>()
    val inProgress = MutableLiveData<Boolean>()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        disposables.add(
            networkRepository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { inProgress.value = true }
                .subscribe({ pagingData: PagingData<Post> ->
                    data.value = pagingData
                    inProgress.value = false
                }, Timber::e)
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}