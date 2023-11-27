package com.cosmicrockets.ui.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.cosmicrockets.R
import com.cosmicrockets.app.App
import com.cosmicrockets.databinding.DialogSettingsBinding
import com.cosmicrockets.databinding.FragmentRocketBinding
import com.cosmicrockets.domain.api.interactor.SettingsSavingInteractor
import com.cosmicrockets.presentation.mapper.SettingsFromRocketDataMapper
import com.cosmicrockets.presentation.models.RocketDataRV
import com.cosmicrockets.presentation.models.RocketInfo
import com.cosmicrockets.presentation.rocket.SharedRocketViewModel
import com.cosmicrockets.ui.launches.LaunchesFragment
import com.cosmicrockets.ui.state.RocketRecyclerState
import com.google.android.material.bottomsheet.BottomSheetDialog
import javax.inject.Inject


class RocketFragment : Fragment() {
    private lateinit var binding: FragmentRocketBinding
    private var rocket: RocketInfo? = null

    private val sharedViewModel: SharedRocketViewModel by activityViewModels()

    @Inject
    lateinit var settingsSavingInteractor: SettingsSavingInteractor

    private var rocketData = mutableListOf<RocketDataRV>()

    private val rocketAdapter = RocketRecyclerAdapter(rocketData)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as App).appComponent.inject(this)
        binding = FragmentRocketBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rocket = requireArguments().getParcelable("rocket")
        bindViews()
        rocketData.addAll(bindBySharedPrefs())

        sharedViewModel.state.observe(viewLifecycleOwner) {
            render(it)
            rocketAdapter.notifyDataSetChanged()
        }
    }

    private fun bindViews() = with(binding) {

        nameTv.text = rocket?.name

        imageIv.load(rocket?.image) {
            crossfade(true)
            //placeholder(R.drawable.place_holder_icon)
            //error(R.drawable.place_holder_icon)
        }

        settingsBtn.setOnClickListener {
            showDialog()
        }

        binding.rocketInfoRv.adapter = rocketAdapter

        firstFlightTv.text = rocket?.firstFlight
        countryTv.text = rocket?.country
        costPerLaunchTv.text = rocket?.costPerLaunch

        firstEnginesTv.text = rocket?.firstEngines
        firstFuelAmountTonsTv.text = rocket?.firstFuelAmountTons
        firstBurnTimeSecTv.text = rocket?.firstBurnTimeSec

        secondEnginesTv.text = rocket?.secondEngines
        secondFuelAmountTonsTv.text = rocket?.secondFuelAmountTons
        secondBurnTimeSecTv.text = rocket?.secondBurnTimeSec

        launchesButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", rocket?.name)
            bundle.putString("id", rocket?.id)

            val launchesFragment = LaunchesFragment()
            launchesFragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, launchesFragment, "rockets_fragment")
                ?.addToBackStack(null)
                ?.commit()

        }

    }

    private fun showDialog() {
        BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetDialogTheme
        ).apply {
            val dialogBinding = DialogSettingsBinding.inflate(LayoutInflater.from(requireContext()))
            setContentView(dialogBinding.root)

            val settings = settingsSavingInteractor.getSettings()

            with(dialogBinding){

                heightSwitch.isChecked = settings.height
                diameterSwitch.isChecked = settings.diameter
                massSwitch.isChecked = settings.mass
                payloadWeightSwitch.isChecked = settings.payloadWeight


                closeBtn.setOnClickListener {
                    dismiss()
                }

                heightSwitch.setOnCheckedChangeListener { _, checked ->
                    sharedViewModel.setState(RocketRecyclerState.Height(checked))
                    settingsSavingInteractor.saveSettings(SettingsFromRocketDataMapper.map(rocketData))
                }

                diameterSwitch.setOnCheckedChangeListener { _, checked ->
                    sharedViewModel.setState(RocketRecyclerState.Diameter(checked))
                    settingsSavingInteractor.saveSettings(SettingsFromRocketDataMapper.map(rocketData))
                }

                massSwitch.setOnCheckedChangeListener { _, checked ->
                    sharedViewModel.setState(RocketRecyclerState.Mass(checked))
                    settingsSavingInteractor.saveSettings(SettingsFromRocketDataMapper.map(rocketData))
                }

                payloadWeightSwitch.setOnCheckedChangeListener { _, checked ->
                    sharedViewModel.setState(RocketRecyclerState.PayloadWeight(checked))
                    settingsSavingInteractor.saveSettings(SettingsFromRocketDataMapper.map(rocketData))
                }
            }



            show()
        }
    }

    private fun bindBySharedPrefs(): List<RocketDataRV> {
        val settings = settingsSavingInteractor.getSettings()
        val result = mutableListOf<RocketDataRV>()

        if (settings.height) {
            result.add(RocketDataRV(rocket?.heightFeet!!, "Высота, ", FEET))
        } else {
            result.add(RocketDataRV(rocket?.heightMeters!!, "Высота, ", METER))
        }
        if (settings.diameter) {
            result.add(RocketDataRV(rocket?.diameterFeet!!, "Диаметр, ", FEET))
        } else {
            result.add(RocketDataRV(rocket?.diameterMeters!!, "Диаметр, ", METER))
        }
        if (settings.mass) {
            result.add(RocketDataRV(rocket?.massLb!!, "Масса, ", LIBRA))
        } else {
            result.add(RocketDataRV(rocket?.massKg!!, "Масса, ", KILOGRAM))
        }
        if (settings.payloadWeight) {
            result.add(RocketDataRV(rocket?.payloadWeightLb!!, "Нагрузка, ", LIBRA))
        } else {
            result.add(RocketDataRV(rocket?.payloadWeightKg!!, "Нагрузка, ", KILOGRAM))
        }
        return result
    }

    private fun render(state: RocketRecyclerState) {
        when (state) {
            is RocketRecyclerState.Height -> bindHeight(state.boolean)
            is RocketRecyclerState.Diameter -> bindDiameter(state.boolean)
            is RocketRecyclerState.Mass -> bindMass(state.boolean)
            is RocketRecyclerState.PayloadWeight -> bindPayloadWeight(state.boolean)
        }
    }

    private fun bindHeight(boolean: Boolean) {
        rocketData.removeAt(0)
        if (boolean) {
            rocketData.add(0, RocketDataRV(rocket?.heightFeet!!, "Высота, ", FEET))
        } else {
            rocketData.add(0, RocketDataRV(rocket?.heightMeters!!, "Высота, ", METER))
        }
    }

    private fun bindDiameter(boolean: Boolean) {
        rocketData.removeAt(1)
        if (boolean) {
            rocketData.add(1, RocketDataRV(rocket?.diameterFeet!!, "Диаметр, ", FEET))
        } else {
            rocketData.add(1, RocketDataRV(rocket?.diameterMeters!!, "Диаметр, ", METER))
        }
    }

    private fun bindMass(boolean: Boolean) {
        rocketData.removeAt(2)
        if (boolean) {
            rocketData.add(2, RocketDataRV(rocket?.massLb!!, "Масса, ", LIBRA))
        } else {
            rocketData.add(2, RocketDataRV(rocket?.massKg!!, "Масса, ", KILOGRAM))
        }
    }

    private fun bindPayloadWeight(boolean: Boolean) {
        rocketData.removeAt(3)
        if (boolean) {
            rocketData.add(3, RocketDataRV(rocket?.payloadWeightLb!!, "Нагрузка, ", LIBRA))
        } else {
            rocketData.add(3, RocketDataRV(rocket?.payloadWeightKg!!, "Нагрузка, ", KILOGRAM))
        }
    }

    private companion object {
        const val METER = "m"
        const val FEET = "ft"
        const val KILOGRAM = "kg"
        const val LIBRA = "lb"
    }

}