package com.example.maden.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maden.databinding.ItemRcTimeBinding
import com.example.maden.model.TimerSaveModel

class TimerSaveAdapter : ListAdapter<TimerSaveModel,TimerViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder {
        val binding =
            ItemRcTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun addItem(second:String,minus:String){
        val cacheList = currentList.toMutableList()
        val newList = ArrayList<TimerSaveModel>()
        newList.add(TimerSaveModel(second, minus))
        if (cacheList.size >0){
            newList.addAll(cacheList)
        }else{
            newList.addAll(cacheList.reversed())
        }
        submitList(newList)
    }


}

private class DiffCallback : DiffUtil.ItemCallback<TimerSaveModel>() {
    override fun areItemsTheSame(oldItem: TimerSaveModel, newItem: TimerSaveModel): Boolean {
        return oldItem.second == newItem.second
    }

    override fun areContentsTheSame(oldItem: TimerSaveModel, newItem: TimerSaveModel): Boolean {
        return oldItem.minus == newItem.minus
    }

}

class TimerViewHolder(private val binding: ItemRcTimeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(timerData: TimerSaveModel?) {
                binding.tvRcSecond.text = timerData?.second
                 binding.tvRcMinus.text = timerData?.minus
        }
    }