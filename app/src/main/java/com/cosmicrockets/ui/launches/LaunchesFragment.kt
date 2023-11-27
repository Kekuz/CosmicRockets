package com.cosmicrockets.ui.launches

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cosmicrockets.R
import com.cosmicrockets.app.App
import com.cosmicrockets.databinding.FragmentLaunchesBinding
import com.cosmicrockets.domain.api.interactor.DatabaseInteractor
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.presentation.launches.LaunchFactory
import com.cosmicrockets.presentation.launches.LaunchViewModel
import com.cosmicrockets.ui.state.LaunchesFragmentState
import javax.inject.Inject


class LaunchesFragment : Fragment() {
    private lateinit var binding: FragmentLaunchesBinding
    private lateinit var viewModel: LaunchViewModel


    @Inject
    lateinit var searchLaunchByIdUseCase: SearchLaunchByIdUseCase

    @Inject
    lateinit var databaseInteractor: DatabaseInteractor

    private lateinit var currentState: LaunchesFragmentState

    private val onEndingList: () -> Unit =
        {
            if (currentState is LaunchesFragmentState.Content && viewModel.hasNextPage) {
                viewModel.request()
            }
        }

    private val launches = mutableListOf<Launch>()
    private val launchAdapter = LaunchAdapter(launches, onEndingList)

    private var rocketId: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as App).appComponent.inject(this)
        binding = FragmentLaunchesBinding.inflate(inflater, container, false)

        rocketId = requireArguments().getString("id")


        viewModel = ViewModelProvider(
            this,
            LaunchFactory(rocketId!!, searchLaunchByIdUseCase, databaseInteractor)
        )[LaunchViewModel::class.java]

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rocketNameTv.text = requireArguments().getString("name")

        binding.launchRv.adapter = launchAdapter

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.state.observe(activity as LifecycleOwner) {
            currentState = it
            render(it)
        }

        binding.launchSwipeRefresh.setProgressBackgroundColorSchemeResource(R.color.black)
        val swipeColor = resources.getColor(R.color.light_gray)
        binding.launchSwipeRefresh.setColorSchemeColors(swipeColor, swipeColor, swipeColor)

        binding.launchSwipeRefresh.setOnRefreshListener {
            launches.clear()
            viewModel.recyclerRefreshed()
            binding.launchSwipeRefresh.isRefreshing = false
        }

    }

    private fun render(state: LaunchesFragmentState) {
        when (state) {
            is LaunchesFragmentState.Loading -> showLoading()
            is LaunchesFragmentState.LoadingBottom -> showLoadingBottom()
            is LaunchesFragmentState.Error -> showError(state.data, state.message)
            is LaunchesFragmentState.Content -> showLaunches(state.data)
        }
    }

    private fun showLoadingBottom() {
        binding.launchesLoadingBottomPb.isVisible = true
    }

    private fun showLoading() {
        binding.launchesLoadingPb.isVisible = true
    }

    private fun showLaunches(listLaunches: List<Launch>) {
        launches.addAll(listLaunches)
        launchAdapter.notifyItemRangeChanged(launches.size, listLaunches.size)
        binding.launchesLoadingPb.isVisible = false
        binding.launchesLoadingBottomPb.isVisible = false
        binding.launchesErrorPb.isVisible = false
        binding.launchesEmptyTv.isVisible = listLaunches.isEmpty()
    }

    private fun showError(listLaunches: List<Launch>, message: String) {
        launches.clear()
        launches.addAll(listLaunches)
        launchAdapter.notifyDataSetChanged()
        binding.launchesLoadingPb.isVisible = false
        binding.launchesLoadingBottomPb.isVisible = false
        binding.launchesErrorPb.isVisible = true
        binding.launchesEmptyTv.isVisible = false
    }
}