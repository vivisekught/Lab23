package com.example.lab2.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R

class UserChoicesHistoryFragment : Fragment() {

    private lateinit var viewModel: UserChoicesHistoryViewModel
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_choices_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UserChoicesHistoryViewModel::class.java]
        viewModel.userChoices.observe(viewLifecycleOwner){
            if (it.isNotEmpty()) {
                setupRecyclerView(view)
            } else {
                view.findViewById<TextView>(R.id.empty_history).isVisible = true
            }
        }
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.history_rv)
        historyAdapter = HistoryAdapter(
            viewModel.userChoices.value ?: listOf()
        )
        recyclerView.adapter = historyAdapter
    }


    companion object {
        fun newInstance() = UserChoicesHistoryFragment()
    }
}