package com.cosmicrockets.ui.launches

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cosmicrockets.app.App
import com.cosmicrockets.databinding.FragmentLaunchesBinding
import com.cosmicrockets.domain.impl.SearchLaunchByIdUseCaseImpl
import com.cosmicrockets.domain.impl.SearchRocketsUseCaseImpl
import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.presentation.launches.LaunchFactory
import com.cosmicrockets.presentation.launches.LaunchViewModel
import com.cosmicrockets.presentation.rockets_viewpager.RocketsVPViewModel
import com.cosmicrockets.ui.state.LaunchesFragmentState



class LaunchesFragment : Fragment() {
    private lateinit var binding: FragmentLaunchesBinding

    private lateinit var viewModel: LaunchViewModel

    private var id : String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as App).appComponent.inject(this)
        binding = FragmentLaunchesBinding.inflate(inflater, container, false)

        id = requireArguments().getString("id")

        //Тут надо жестко заинджектить зависимость
        viewModel = ViewModelProvider(this, LaunchFactory(id!!, SearchLaunchByIdUseCaseImpl()))[LaunchViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rocketNameTv.text = requireArguments().getString("name")

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.state.observe(activity as LifecycleOwner){
            render(it)
        }



    }
    private fun render(state: LaunchesFragmentState) {
        when (state) {
            is LaunchesFragmentState.Loading -> showLoading()
            is LaunchesFragmentState.Error -> showError(state.message)
            is LaunchesFragmentState.Content -> showLaunches(state.data)
        }
    }

    private fun showLoading() {
        //binding.rocketLoadingPb.isVisible = true
    }

    private fun showLaunches(listLaunches: List<Launch>) {
        binding.launchRv.adapter = LaunchAdapter(listLaunches)

        /*binding.errorTv.isVisible = false
        binding.reloadBtn.isVisible = false
        binding.rocketLoadingPb.isVisible = false*/
    }

    private fun showError(message: String) {
        /*binding.rocketLoadingPb.isVisible = false
        binding.errorTv.isVisible = true
        binding.reloadBtn.isVisible = true
        binding.errorTv.text = message*/
    }
}