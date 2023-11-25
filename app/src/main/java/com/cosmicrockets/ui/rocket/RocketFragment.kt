package com.cosmicrockets.ui.rocket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.cosmicrockets.R
import com.cosmicrockets.databinding.DialogSettingsBinding
import com.cosmicrockets.databinding.FragmentRocketBinding
import com.cosmicrockets.presentation.models.RocketDataRV
import com.cosmicrockets.presentation.models.RocketInfo
import com.cosmicrockets.presentation.rocket.SharedRocketViewModel
import com.cosmicrockets.ui.state.RocketRecyclerState
import com.google.android.material.bottomsheet.BottomSheetDialog


class RocketFragment : Fragment() {
    private lateinit var binding: FragmentRocketBinding
    private var rocket: RocketInfo? = null

    private val sharedViewModel: SharedRocketViewModel by activityViewModels()


    private var rocketData = mutableListOf<RocketDataRV>()

    private val rocketAdapter = RocketRecyclerAdapter(rocketData)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        //bindRecyclerView()
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

            findNavController().navigate(R.id.action_rocketsVPFragment_to_launchesFragment, bundle)
        }

    }

    private fun showDialog() {
        BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetDialogTheme
        ).apply {
            val dialogBinding = DialogSettingsBinding.inflate(LayoutInflater.from(requireContext()))
            setContentView(dialogBinding.root)

            //тут из шаредпрефс достаем значения и выставляем ползунки

            dialogBinding.closeBtn.setOnClickListener {
                dismiss()
            }

            dialogBinding.heightSwitch.setOnCheckedChangeListener { switcher, checked ->
                sharedViewModel.setState(RocketRecyclerState.Height(checked))
                //Тут юзкейс чтобы менять состояние в шаред префс
            }

            dialogBinding.diameterSwitch.setOnCheckedChangeListener { switcher, checked ->
                sharedViewModel.setState(RocketRecyclerState.Diameter(checked))
            }

            dialogBinding.massSwitch.setOnCheckedChangeListener { switcher, checked ->
                sharedViewModel.setState(RocketRecyclerState.Mass(checked))
            }

            dialogBinding.payloadWeightSwitch.setOnCheckedChangeListener { switcher, checked ->
                sharedViewModel.setState(RocketRecyclerState.PayloadWeight(checked))
            }

            show()
        }
    }

    //Тут в зависимости от sharedPrefs нужно будет указывать единицы и значения
    //Пока тут по дефолту m и kg
    private fun bindBySharedPrefs(): List<RocketDataRV> {
        return listOf(
            RocketDataRV(rocket?.heightMeters!!, "Высота, ", METER),
            RocketDataRV(rocket?.diameterMeters!!, "Диаметр, ", METER),
            RocketDataRV(rocket?.massKg!!, "Масса, ", KILOGRAM),
            RocketDataRV(rocket?.payloadWeightKg!!, "Нагрузка, ", KILOGRAM),
        )
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