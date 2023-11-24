package com.cosmicrockets.ui.launches

import androidx.recyclerview.widget.RecyclerView
import com.cosmicrockets.R
import com.cosmicrockets.databinding.LaunchViewBinding
import com.cosmicrockets.domain.models.launch.Launch

class LaunchViewHolder(private val binding: LaunchViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Launch) = with(binding) {

        launchNameTv.text = model.name
        launchDateTv.text = model.date
        if(model.success != null){
            if(model.success){
                successIv.setBackgroundResource(R.drawable.launch_successful)
            }else{
                successIv.setBackgroundResource(R.drawable.launch_failed)
            }
        }


    }

}
