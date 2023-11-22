package com.cosmicrockets.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cosmicrockets.data.network.RetrofitNetworkClient
import com.cosmicrockets.data.network.SpaceXAPI
import com.cosmicrockets.data.network.dto.rocket.RocketSearchResponse
import com.cosmicrockets.data.repository.RocketRepositoryImpl
import com.cosmicrockets.databinding.ActivityMainBinding
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase
import com.cosmicrockets.domain.impl.SearchRocketsUseCaseImpl
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.ui.rocket.RocketFragment
import com.cosmicrockets.ui.rocket.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private val searchRocketsUseCase = SearchRocketsUseCaseImpl(RocketRepositoryImpl(RetrofitNetworkClient(this)))
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        searchRocketsUseCase.execute(object : SearchRocketsUseCase.RocketConsumer {
            override fun consume(foundRockets: List<Rocket>?, errorMessage: String?) {
                CoroutineScope(Dispatchers.IO).launch {
                    if (foundRockets != null) {
                        Log.e("Response", foundRockets.toString())

                        runOnUiThread{
                            val viewPagerAdapter = ViewPagerAdapter(this@MainActivity,mapFragment(foundRockets))
                            binding.viewPager.adapter = viewPagerAdapter
                            TabLayoutMediator(
                                binding.tabLayout,
                                binding.viewPager
                            ) { _, _ -> //tab.text = fragListTitles[pos]
                            }.attach()
                        }


                        //rocketList.addAll(foundRockets)
                    }
                    if (errorMessage != null) {
                        //_placeholderLiveData.postValue(errorMessage.toString())
                    } else {
                        //_placeholderLiveData.postValue("-")
                    }
                }
            }

        })



    }

    private fun mapFragment(input: List<Rocket>): List<RocketFragment>{
        return input.map{ RocketFragment(it) }
    }
}