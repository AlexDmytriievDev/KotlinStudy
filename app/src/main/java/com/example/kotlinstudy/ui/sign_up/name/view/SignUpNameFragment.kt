package com.example.kotlinstudy.ui.sign_up.name.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.kotlinstudy.databinding.FragmentSignUpNameBinding
import com.example.kotlinstudy.ui.sign_up.name.viewmodel.SignUpNameViewModel
import com.example.kotlinstudy.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpNameFragment : Fragment() {

    private val viewModel: SignUpNameViewModel by viewModels()
    private lateinit var binding: FragmentSignUpNameBinding

    override fun onCreateView(i: LayoutInflater, v: ViewGroup?, b: Bundle?): View? {
        binding = FragmentSignUpNameBinding.inflate(i, v, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigation = Navigation.findNavController(view)

        binding.signUpAppBar.setOnClickListener { navigation.popBackStack() }

        viewModel.isAllDataFilled.observe(viewLifecycleOwner, {
            hideKeyboard()
            navigation.navigate(SignUpNameFragmentDirections.toSignUpPassword(viewModel.user.value))
        })
    }
}
