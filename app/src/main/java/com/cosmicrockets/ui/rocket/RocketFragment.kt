package com.cosmicrockets.ui.rocket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.cosmicrockets.databinding.FragmentLaunchesBinding
import com.cosmicrockets.databinding.FragmentRocketBinding
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.presentation.models.RocketInfo


class RocketFragment : Fragment() {
    private lateinit var binding: FragmentRocketBinding
    private var rocket: RocketInfo? = null

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
        firstFlightTv.text = rocket?.firstFlight
        countryTv.text = rocket?.country
        costPerLaunchTv.text = rocket?.costPerLaunch

        firstEnginesTv.text= rocket?.firstEngines
        firstFuelAmountTonsTv.text = rocket?.firstFuelAmountTons
        firstBurnTimeSecTv.text = rocket?.firstBurnTimeSec

        secondEnginesTv.text= rocket?.secondEngines
        secondFuelAmountTonsTv.text = rocket?.secondFuelAmountTons
        secondBurnTimeSecTv.text = rocket?.secondBurnTimeSec

    }

}