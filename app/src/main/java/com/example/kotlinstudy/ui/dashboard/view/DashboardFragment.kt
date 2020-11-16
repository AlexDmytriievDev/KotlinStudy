package com.example.kotlinstudy.ui.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.kotlinstudy.databinding.FragmentDashboardBinding
import com.example.kotlinstudy.model.post.Post
import com.example.kotlinstudy.ui.dashboard.adapter.DashboardAdapter
import com.example.kotlinstudy.ui.dashboard.adapter.DashboardLoadingAdapter
import com.example.kotlinstudy.ui.dashboard.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment(), DashboardAdapter.OnPostClickListener {

    @Inject
    lateinit var adapter: DashboardAdapter

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var navigation: NavController

    override fun onCreateView(i: LayoutInflater, v: ViewGroup?, b: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(i, v, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.dashboardRecyclerView.adapter =
            adapter.withLoadStateFooter(DashboardLoadingAdapter { adapter.retry() })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation = Navigation.findNavController(view)

        viewModel.data.observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })

        adapter.listener = this

        binding.apply {
            dashboardMenu.setOnClickListener { }
            dashboardNotifications.setOnClickListener { }
        }
    }

    override fun onPostClick(post: Post?) {
        post?.apply { navigation.navigate(DashboardFragmentDirections.toPost(this)) }
    }
}
