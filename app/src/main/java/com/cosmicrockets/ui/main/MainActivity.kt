package com.cosmicrockets.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cosmicrockets.databinding.ActivityMainBinding
import com.cosmicrockets.ui.rocket.RocketFragment
import com.cosmicrockets.ui.rocket.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    //Чтобы получить этот список, мы дерзко идем в сеть и узнаем, какие у нас есть ракеты
    private val fragList = listOf(
        RocketFragment("Ракета 1"),
        RocketFragment("Ракета 2"),
        RocketFragment("Ракета 3"),
    )
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPagerAdapter = ViewPagerAdapter(this, fragList)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { _, _ -> //tab.text = fragListTitles[pos]
        }.attach()
    }
}