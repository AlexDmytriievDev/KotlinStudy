package com.example.kotlinstudy.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.ActivityBaseSingleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseSingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityBaseSingleBinding>(this, R.layout.activity_base_single)
    }
}