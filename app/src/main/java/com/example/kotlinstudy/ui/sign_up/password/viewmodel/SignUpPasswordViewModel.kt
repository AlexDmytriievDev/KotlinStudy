package com.example.kotlinstudy.ui.sign_up.password.viewmodel

import android.content.Context
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SignUpPasswordViewModel @ViewModelInject constructor(
    @ApplicationContext
    private val context: Context,
    private val rxUtils: RxUtils,
    private val dataBaseRepository: DataBaseRepository,
    private val disposables: CompositeDisposable
) : ViewModel() {

    val user = MutableLiveData<User>()
    val passwordError = MutableLiveData<String>()
    val repeatPassword = MutableLiveData<String>()
    val repeatPasswordError = MutableLiveData<String>()
    val inProgress = MutableLiveData<Boolean>()

    val isAccountCreated = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun createAccount() {
        super.onCleared()
        if (isValidCredentials()) {
            disposables.add(
                rxUtils.zipWithTimer(dataBaseRepository.addUser(user.value!!.apply {
                    isSignIn = true
                }), Constants.DELAY.BASE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { inProgress.value = true }
                    .doFinally { inProgress.value = false }
                    .subscribe({ success: Boolean ->
                        isAccountCreated.value = success
                    }, Timber::e)
            )
        }
    }

    private fun isValidCredentials(): Boolean {
        when {
            user.value?.password.isNullOrEmpty() -> {
                passwordError.value = context.getString(R.string.error_empty)
            }
            user.value?.password?.length ?: 0 < Constants.NUM.PASSWORD_MIN_LENGTH -> {
                passwordError.value = context.getString(R.string.error_password)
            }
            repeatPassword.value.isNullOrEmpty() -> {
                repeatPasswordError.value = context.getString(R.string.error_empty)
            }
            repeatPassword.value?.length ?: 0 < Constants.NUM.PASSWORD_MIN_LENGTH -> {
                repeatPasswordError.value = context.getString(R.string.error_password)
            }
            user.value?.password != repeatPassword.value -> {
                repeatPasswordError.value = context.getString(R.string.error_repeat)
            }
            else -> return true
        }
        return false
    }
}