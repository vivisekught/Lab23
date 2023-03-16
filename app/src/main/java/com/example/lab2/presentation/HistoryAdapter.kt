package com.example.lab2.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.databinding.LayoutUserChoiceBinding
import com.example.lab2.domain.UserChoice

class HistoryAdapter (private val userChoices: List<UserChoice>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(val binding: LayoutUserChoiceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = LayoutUserChoiceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val image = userChoices[position]
        holder.binding.flags.text = image.flags
        holder.binding.figures.text = image.figures
        }

    override fun getItemCount(): Int {
        return userChoices.size
    }
}