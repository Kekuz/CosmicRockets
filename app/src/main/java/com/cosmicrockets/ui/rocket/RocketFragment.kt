package com.cosmicrockets.ui.rocket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cosmicrockets.databinding.FragmentLaunchesBinding
import com.cosmicrockets.databinding.FragmentRocketBinding


class RocketFragment(private val name: String) : Fragment() {
    private lateinit var binding: FragmentRocketBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.name.text = name
    }
}