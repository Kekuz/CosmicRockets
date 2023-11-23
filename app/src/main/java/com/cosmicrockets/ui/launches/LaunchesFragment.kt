package com.cosmicrockets.ui.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cosmicrockets.R
import com.cosmicrockets.databinding.FragmentLaunchesBinding
import com.cosmicrockets.databinding.FragmentRocketBinding
import com.cosmicrockets.presentation.models.RocketInfo


class LaunchesFragment : Fragment() {
    private lateinit var binding: FragmentLaunchesBinding

    private var id : String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchesBinding.inflate(inflater, container, false)

        id = requireArguments().getString("id")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rocketNameTv.text = requireArguments().getString("name")
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}