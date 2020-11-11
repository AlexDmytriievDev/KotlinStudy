package com.example.kotlinstudy.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.ViewAppBarBinding

class AppBarView : FrameLayout {

    private lateinit var binding: ViewAppBarBinding

    constructor(c: Context) : super(c) {
        init(null)
    }

    constructor(c: Context, a: AttributeSet?) : super(c, a) {
        init(a)
    }

    constructor(c: Context, a: AttributeSet?, s: Int) : super(c, a, s) {
        init(a)
    }

    constructor(c: Context, a: AttributeSet?, s: Int, r: Int) : super(c, a, s, r) {
        init(a)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.appBarBackBtn.setOnClickListener(l)
    }

    private fun init(attrs: AttributeSet?) {
        binding = ViewAppBarBinding.inflate(
            context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            ) as LayoutInflater, this, true
        )

        attrs?.apply {
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.AppBarView, 0, 0
            ).apply {
                binding.appBarTitle.text = getString(R.styleable.AppBarView_title)
            }
        }
    }
}