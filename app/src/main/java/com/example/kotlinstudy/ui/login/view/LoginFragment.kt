package com.example.kotlinstudy.ui.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.FragmentLoginBinding
import com.example.kotlinstudy.ui.login.viewmodel.LoginViewModel
import com.example.kotlinstudy.utils.errorToast
import com.example.kotlinstudy.utils.hideKeyboard
import com.example.kotlinstudy.utils.setWhiteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWhiteTheme()
    }

    override fun onCreateView(i: LayoutInflater, v: ViewGroup?, b: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(i, v, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigation = Navigation.findNavController(view)

        viewModel.userLoggedInEvent.observe(viewLifecycleOwner, {
            hideKeyboard()
            if (it) navigation.navigate(LoginFragmentDirections.toDashboard())
            else errorToast(getString(R.string.error_user))
        })

        binding.loginSignUpBtn.setOnClickListener {
            navigation.navigate(LoginFragmentDirections.toSignUp())
        }
    }
}