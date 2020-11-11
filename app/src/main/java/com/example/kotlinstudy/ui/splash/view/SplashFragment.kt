package com.example.kotlinstudy.ui.splash.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.kotlinstudy.databinding.FragmentSplashBinding
import com.example.kotlinstudy.ui.splash.viewmodel.SplashViewModel
import com.example.kotlinstudy.utils.setSplashTheme
import com.example.kotlinstudy.utils.setWhiteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setSplashTheme()
    }

    override fun onCreateView(i: LayoutInflater, v: ViewGroup?, b: Bundle?): View? {
        val binding = FragmentSplashBinding.inflate(i, v, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigation = Navigation.findNavController(view)

        viewModel.isUserSignIn.observe(viewLifecycleOwner, {
            if (it) navigation.navigate(SplashFragmentDirections.toDashboard())
            else navigation.navigate(SplashFragmentDirections.toLogin())
        })
        viewModel.getSignInUser()
    }

    override fun onDetach() {
        super.onDetach()
        setWhiteTheme()
    }
}