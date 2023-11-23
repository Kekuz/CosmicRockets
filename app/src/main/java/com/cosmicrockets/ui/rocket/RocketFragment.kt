package com.cosmicrockets.ui.rocket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.cosmicrockets.R
import com.cosmicrockets.databinding.FragmentLaunchesBinding
import com.cosmicrockets.databinding.FragmentRocketBinding
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.presentation.models.RocketDataRV
import com.cosmicrockets.presentation.models.RocketInfo
import com.cosmicrockets.ui.launches.LaunchesFragment


class RocketFragment : Fragment() {
    private lateinit var binding: FragmentRocketBinding
    private var rocket: RocketInfo? = null

    //Надо бы сделать ViewModel...
    private val rocketData = mutableListOf(
        RocketDataRV("-","Высота, -"),
        RocketDataRV("-","Диаметр, -"),
        RocketDataRV("-","Масса, -"),
        RocketDataRV("-","Нагрузка, -"),
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rocket  = requireArguments().getParcelable("rocket")
        bindViews()
    }

    private fun bindViews() = with(binding){

        nameTv.text = rocket?.name
        imageIv.load(rocket?.image) {
            crossfade(true)
            //placeholder(R.drawable.place_holder_icon)
            //error(R.drawable.place_holder_icon)
        }
        bindRecyclerView()
        binding.rocketInfoRv.adapter = RocketRecyclerAdapter(rocketData)

        firstFlightTv.text = rocket?.firstFlight
        countryTv.text = rocket?.country
        costPerLaunchTv.text = rocket?.costPerLaunch

        firstEnginesTv.text= rocket?.firstEngines
        firstFuelAmountTonsTv.text = rocket?.firstFuelAmountTons
        firstBurnTimeSecTv.text = rocket?.firstBurnTimeSec

        secondEnginesTv.text= rocket?.secondEngines
        secondFuelAmountTonsTv.text = rocket?.secondFuelAmountTons
        secondBurnTimeSecTv.text = rocket?.secondBurnTimeSec

        launchesButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", rocket?.name)
            bundle.putString("id",rocket?.id)

            val launchesFragment = LaunchesFragment()

            //Говно
            parentFragmentManager.beginTransaction().replace(R.id.viewPager, launchesFragment).commit()
            //findNavController().navigate(R.id.action_rocketsFragment_to_launchesFragment, bundle)
        }

    }

    private fun bindRecyclerView(){
        rocketData[0].num = rocket?.heightMeters!!
        rocketData[1].num = rocket?.diameterMeters!!
        rocketData[2].num = rocket?.massKg!!
        rocketData[3].num = rocket?.payloadWeightKg!!
    }

}