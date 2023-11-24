package com.cosmicrockets.ui.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosmicrockets.databinding.LaunchViewBinding
import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.ui.state.LaunchesFragmentState

class LaunchAdapter(
    private val launches: List<Launch>,
    private val onEndingList: () -> Unit,
) :
    RecyclerView.Adapter<LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LaunchViewBinding.inflate(inflater, parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position])

        if (position == launches.size - 1) {
            onEndingList.invoke()
        }

    }

    override fun getItemCount(): Int {
        return launches.size
    }
}