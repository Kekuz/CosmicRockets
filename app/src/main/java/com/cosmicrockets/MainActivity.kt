package com.cosmicrockets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cosmicrockets.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    //Чтобы получить этот список, мы дерзко идем в сеть и узнаем, какие у нас есть ракеты
    private val fragList = listOf(
        RocketFragment(),
        RocketFragment(),
        RocketFragment(),
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