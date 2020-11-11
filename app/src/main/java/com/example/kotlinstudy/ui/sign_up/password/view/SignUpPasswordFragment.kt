package com.example.kotlinstudy.ui.sign_up.password.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.FragmentSignUpPasswordBinding
import com.example.kotlinstudy.ui.sign_up.password.viewmodel.SignUpPasswordViewModel
import com.example.kotlinstudy.utils.errorToast
import com.example.kotlinstudy.utils.hideKeyboard
import com.example.kotlinstudy.utils.successToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpPasswordFragment : Fragment() {

    private val viewModel: SignUpPasswordViewModel by viewModels()
    private lateinit var binding: FragmentSignUpPasswordBinding

    override fun onCreateView(i: LayoutInflater, v: ViewGroup?, b: Bundle?): View? {
        binding = FragmentSignUpPasswordBinding.inflate(i, v, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigation = Navigation.findNavController(view)

        val args: SignUpPasswordFragmentArgs by navArgs()
        viewModel.user.value = args.user

        binding.signUpAppBar.setOnClickListener { navigation.popBackStack() }

        viewModel.isAccountCreated.observe(viewLifecycleOwner, {
            hideKeyboard()
            if (it) {
                successToast(getString(R.string.account_created))
                navigation.navigate(SignUpPasswordFragmentDirections.toDashboard())
            } else errorToast(getString(R.string.error_unknown))
        })
    }
}
