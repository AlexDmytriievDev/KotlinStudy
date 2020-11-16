package com.example.kotlinstudy.ui.sign_up.name.viewmodel

import android.content.Context
import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinstudy.R
import com.example.kotlinstudy.model.User
import com.example.kotlinstudy.utils.Constants
import com.example.kotlinstudy.utils.SingleLiveEvent
import dagger.hilt.android.qualifiers.ApplicationContext

class SignUpNameViewModel @ViewModelInject constructor(
    @ApplicationContext
    private val context: Context
) : ViewModel() {

    val user = MutableLiveData<User>()
    val emailError = MutableLiveData<String>()
    val firstNameError = MutableLiveData<String>()
    val lastNameError = MutableLiveData<String>()

    val isAllDataFilled = SingleLiveEvent<Boolean>()

    init {
        user.value = User()
    }

    fun verifyData() {
        when {
            user.value?.email.isNullOrEmpty() -> {
                emailError.value = context.getString(R.string.error_empty)
            }
            !Patterns.EMAIL_ADDRESS.matcher(user.value?.email ?: Constants.CHAR.EMPTY)
                .matches() -> {
                emailError.value = context.getString(R.string.error_email)
            }
            user.value?.firstName.isNullOrEmpty() -> {
                firstNameError.value = context.getString(R.string.error_empty)
            }
            user.value?.lastName.isNullOrEmpty() -> {
                lastNameError.value = context.getString(R.string.error_empty)
            }
            else -> isAllDataFilled.value = true
        }
    }
}