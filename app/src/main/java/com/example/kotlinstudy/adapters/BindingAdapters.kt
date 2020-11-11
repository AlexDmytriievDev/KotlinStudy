package com.example.kotlinstudy.adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher
import androidx.lifecycle.MutableLiveData
import com.example.kotlinstudy.utils.SingleLiveEvent
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @JvmStatic
    @SuppressLint("RestrictedApi")
    @BindingAdapter("android:onTextChanged")
    fun setListener(view: TextView, onTextChanged: TextViewBindingAdapter.OnTextChanged) {
        setTextWatcher(view, null, onTextChanged, null, null)
    }

    @JvmStatic
    @BindingAdapter("android:onTextChangedObserver")
    fun setListener(view: TextView, error: MutableLiveData<String>) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                error.value = ""
            }
        })
    }

    @JvmStatic
    @BindingAdapter("android:onClickObserver")
    fun setOnClickListener(view: View, event: SingleLiveEvent<Boolean>) {
        view.setOnClickListener { event.value = true }
    }

    @JvmStatic
    @BindingAdapter("android:onActionDoneClick")
    fun setOnDoneClickListener(view: EditText, listener: View.OnClickListener) {
        view.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) listener.onClick(v)
            false
        }
    }

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, value: Boolean?) {
        view.visibility = when (value) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }
}