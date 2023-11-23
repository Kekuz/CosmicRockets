package com.cosmicrockets.ui.rocket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosmicrockets.databinding.RocketInfoViewBinding
import com.cosmicrockets.presentation.models.RocketDataRV

class RocketRecyclerAdapter(private val model: List<RocketDataRV>) :
    RecyclerView.Adapter<RocketRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RocketInfoViewBinding.inflate(inflater, parent, false)
        return RocketRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RocketRecyclerViewHolder, position: Int) {
        holder.bind(model[position])
    }

    override fun getItemCount(): Int {
        return model.size
    }
}