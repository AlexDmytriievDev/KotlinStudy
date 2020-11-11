package com.example.kotlinstudy.ui.login.viewmodel

import android.content.Context
import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinstudy.R
import com.example.kotlinstudy.database.DataBaseRepository
import com.example.kotlinstudy.model.User
import com.example.kotlinstudy.utils.Constants
import com.example.kotlinstudy.utils.RxUtils
import com.example.kotlinstudy.utils.SingleLiveEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class LoginViewModel @ViewModelInject constructor(
    @ApplicationContext
    private val context: Context,
    private val rxUtils: RxUtils,
    private val dataBaseRepository: DataBaseRepository,
    private val disposables: CompositeDisposable
) : ViewModel() {

    val email = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()

    val password = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    val inProgress = MutableLiveData<Boolean>()

    val signUpEvent = SingleLiveEvent<Boolean>()
    val userLoggedInEvent = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun login() {
        if (isValidCredentials()) {
            disposables.add(
                rxUtils.zipWithTimer(dataBaseRepository.getAll(), Constants.DELAY.LOGIN)
                    .flatMap { users: List<User> -> updateUser(users) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { inProgress.value = true }
                    .doFinally { inProgress.value = false }
                    .subscribe({ isLoggedIn: Boolean ->
                        userLoggedInEvent.value = isLoggedIn
                    }, Timber::e)
            )
        }
    }

    private fun updateUser(users: List<User>): Single<Boolean> {
        if (users.isNotEmpty()) {
            for (user in users) {
                if (user.email == email.value && user.password == password.value)
                    user.isSignIn = true
                return dataBaseRepository.updateUser(user)
            }
        }
        return Single.just(false)
    }

    private fun isValidCredentials(): Boolean {
        if (email.value.isNullOrEmpty()) {
            emailError.value = context.getString(R.string.error_empty)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value ?: "").matches()) {
            emailError.value = context.getString(R.string.error_email)
        } else if (password.value.isNullOrEmpty()) {
            passwordError.value = context.getString(R.string.error_empty)
        } else if (password.value?.length ?: 0 < 6) {
            passwordError.value = context.getString(R.string.error_password)
        } else {
            return true
        }
        return false
    }
}
