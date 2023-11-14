package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.SpendingItemBinding
import com.example.myapplication.view.model.SpendModel
import com.example.myapplication.view.model.Type
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class SpendingAdapter(private var list : ArrayList<SpendModel>) :
    RecyclerView.Adapter<SpendingAdapter.SpendingViewHolder>() {

    inner class SpendingViewHolder( val binding: SpendingItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingViewHolder {
        val binding = SpendingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpendingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SpendingViewHolder, position: Int) {
        with(holder) {
            val item = list[position]
            binding.nameTv.text = item.name
            binding.amountTv.text = item.amount.toString()
            if(item.type == Type.SPENDING) {
                binding.icon.setImageResource(R.drawable.expense_icon)
            }
            else{
                binding.icon.setImageResource(R.drawable.income_icon)
            }
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = item.timestamp
            binding.date.text =  SimpleDateFormat("MMM").format(calendar.time) + " " + calendar.get(Calendar.DAY_OF_MONTH)
        }
    }

    fun updateList(list: ArrayList<SpendModel>){
        this.list = list
        notifyDataSetChanged()
    }


}