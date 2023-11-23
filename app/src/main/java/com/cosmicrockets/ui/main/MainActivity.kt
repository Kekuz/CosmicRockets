package com.cosmicrockets.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.cosmicrockets.app.App
import com.cosmicrockets.databinding.ActivityMainBinding
import com.cosmicrockets.presentation.main.MainFactory
import com.cosmicrockets.presentation.main.MainViewModel
import com.cosmicrockets.ui.rocket.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainFactory: MainFactory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, mainFactory)[MainViewModel::class.java]


        viewModel.rocketFragmentsLiveData.observe(this){
            val viewPagerAdapter = ViewPagerAdapter(this, it)
            binding.viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager
            ) { _, _ -> //tab.text = fragListTitles[pos]
            }.attach()
        }
        //Нужно еще обработать ошибочки
    }
}