package com.example.reahometime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reahometime.data.Hometime
import com.example.reahometime.databinding.ListItemHometimeBinding

class HometimeAdapter : ListAdapter<Hometime, RecyclerView.ViewHolder>(HometimeDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HometimeViewHolder(
            ListItemHometimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hometime = getItem(position)
        (holder as HometimeAdapter.HometimeViewHolder).bind(hometime)
    }

    inner class HometimeViewHolder(
        private val binding: ListItemHometimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Hometime) {
            binding.apply {
                hometime = item
                executePendingBindings()
            }
        }
    }

}

private class HometimeDiffCallBack : DiffUtil.ItemCallback<Hometime>() {

    override fun areItemsTheSame(oldItem: Hometime, newItem: Hometime): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Hometime, newItem: Hometime): Boolean {
        return oldItem == newItem
    }

}