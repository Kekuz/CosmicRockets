package com.cosmicrockets.ui.rocket

import androidx.recyclerview.widget.RecyclerView
import com.cosmicrockets.databinding.RocketInfoViewBinding
import com.cosmicrockets.presentation.models.RocketDataRV

class RocketRecyclerViewHolder(private val binding: RocketInfoViewBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(model: RocketDataRV) = with(binding) {
        numberTv.text = model.num
        infoTv.text = model.info
        infoUtilTv.text = model.infoUtil
    }
}