package com.example.kotlinstudy.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlinstudy.R
import es.dmoral.toasty.Toasty

fun Fragment.setSplashTheme() {
    requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    requireActivity().window.statusBarColor = resources.getColor(R.color.malachite)
    requireActivity().window.navigationBarColor = resources.getColor(R.color.purple)
}

fun Fragment.setWhiteTheme() {
    requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    requireActivity().window.statusBarColor = resources.getColor(R.color.white)
    requireActivity().window.navigationBarColor = resources.getColor(R.color.white)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.errorToast(message: String) {
    Toasty.error(
        requireContext(), message,
        Toast.LENGTH_SHORT, false
    ).show()
}

fun Fragment.successToast(message: String) {
    Toasty.success(
        requireContext(), message,
        Toast.LENGTH_SHORT, true
    ).show()
}