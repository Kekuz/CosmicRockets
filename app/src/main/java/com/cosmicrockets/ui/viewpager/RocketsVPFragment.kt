package com.cosmicrockets.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.cosmicrockets.app.App
import com.cosmicrockets.databinding.ViewPagerFragmentBinding
import com.cosmicrockets.presentation.rockets_viewpager.RocketsVPFactory
import com.cosmicrockets.presentation.rockets_viewpager.RocketsVPViewModel
import com.cosmicrockets.ui.rocket.RocketFragment
import com.cosmicrockets.ui.rocket.ViewPagerAdapter
import com.cosmicrockets.ui.state.RocketsVPFragmentState
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class RocketsVPFragment : Fragment() {
    @Inject
    lateinit var rocketsVPFactory: RocketsVPFactory

    private lateinit var viewModel: RocketsVPViewModel
    private lateinit var binding: ViewPagerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as App).appComponent.inject(this)

        binding = ViewPagerFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, rocketsVPFactory)[RocketsVPViewModel::class.java]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(activity as LifecycleOwner) {
            render(it)
        }

        binding.reloadBtn.setOnClickListener {
            viewModel.request()
            binding.errorTv.isVisible = false
            binding.reloadBtn.isVisible = false
        }

    }

    private fun render(state: RocketsVPFragmentState) {
        when (state) {
            is RocketsVPFragmentState.Loading -> showLoading()
            is RocketsVPFragmentState.Error -> showError(state.message)
            is RocketsVPFragmentState.Content -> showProductDetails(state.data)
        }
    }

    private fun showLoading() {
        binding.rocketLoadingPb.isVisible = true
    }

    private fun showProductDetails(listRocketsVPFragment: List<RocketFragment>) {
        val viewPagerAdapter = ViewPagerAdapter(requireActivity(), listRocketsVPFragment)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { _, _ -> //tab.text = fragListTitles[pos]
        }.attach()

        binding.errorTv.isVisible = false
        binding.reloadBtn.isVisible = false
        binding.rocketLoadingPb.isVisible = false
    }

    private fun showError(message: String) {
        binding.rocketLoadingPb.isVisible = false
        binding.errorTv.isVisible = true
        binding.reloadBtn.isVisible = true
        binding.errorTv.text = message
    }
}