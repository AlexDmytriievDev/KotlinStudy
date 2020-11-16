package com.example.kotlinstudy.ui.post.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.kotlinstudy.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(i: LayoutInflater, v: ViewGroup?, b: Bundle?): View? {
        binding = FragmentPostBinding.inflate(i, v, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: PostFragmentArgs by navArgs()
        binding.post = args.post

        val navigation = Navigation.findNavController(view)
        binding.postBackBtn.setOnClickListener { navigation.popBackStack() }
    }
}