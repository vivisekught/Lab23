package com.example.lab2.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab2.R
import com.example.lab2.databinding.MainPageBinding


class MainFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    private var _binding: MainPageBinding? = null
    private val binding: MainPageBinding
        get() = _binding ?: throw RuntimeException("MainPageBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setBindings() {
        with(binding) {
            rgFigures.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.circle -> viewModel.addFigureToResult(MainViewModel.Companion.Figure.Circle)
                    R.id.square -> viewModel.addFigureToResult(MainViewModel.Companion.Figure.Square)
                    R.id.triangle -> viewModel.addFigureToResult(MainViewModel.Companion.Figure.Triangle)
                    R.id.trapezoid -> viewModel.addFigureToResult(MainViewModel.Companion.Figure.Trapezoid)
                }
            }
            cbArea.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.addFlag(MainViewModel.Companion.Flag.Area)
                } else {
                    viewModel.removeFlag(MainViewModel.Companion.Flag.Area)
                }
            }

            cbPerimeter.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.addFlag(MainViewModel.Companion.Flag.Perimeter)
                } else {
                    viewModel.removeFlag(MainViewModel.Companion.Flag.Perimeter)
                }
            }

            okButton.setOnClickListener {
                tvResult.text = viewModel.getResult()
            }
            moveToHistory.setOnClickListener {
                val fragmentManager = requireActivity().supportFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(R.id.main_container_view, UserChoicesHistoryFragment.newInstance())
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.selectionError.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Error selection", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.addedToDb.observe(viewLifecycleOwner){
            if (it){
                Toast.makeText(requireContext(), "Added to history", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}