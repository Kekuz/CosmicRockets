package com.cosmicrockets.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.cosmicrockets.app.App
import com.cosmicrockets.databinding.ViewPagerFragmentBinding
import com.cosmicrockets.presentation.rockets_viewpager.RocketsVPFactory
import com.cosmicrockets.presentation.rockets_viewpager.RocketsVPViewModel
import com.cosmicrockets.ui.rocket.ViewPagerAdapter
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

        viewModel.rocketFragmentsLiveData.observe(activity as LifecycleOwner){
            val viewPagerAdapter = ViewPagerAdapter(requireActivity(), it)
            binding.viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager
            ) { _, _ -> //tab.text = fragListTitles[pos]
            }.attach()
        }
    }
}