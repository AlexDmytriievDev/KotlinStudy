package com.example.kotlinstudy.ui.splash.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinstudy.database.DataBaseRepository
import com.example.kotlinstudy.model.User
import com.example.kotlinstudy.utils.Constants
import com.example.kotlinstudy.utils.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SplashViewModel @ViewModelInject constructor(
    private val rxUtils: RxUtils,
    private val dataBaseRepository: DataBaseRepository,
    private val disposables: CompositeDisposable
) : ViewModel() {

    var isUserSignIn = MutableLiveData<Boolean>()
        private set

    fun getSignInUser() {
        super.onCleared()
        disposables.add(
            //TODO SPLASH
            rxUtils.zipWithTimer(dataBaseRepository.getSignInUser(), Constants.DELAY.BASE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user: User ->
                    isUserSignIn.value = user.isSignIn
                }, Timber::e)
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}