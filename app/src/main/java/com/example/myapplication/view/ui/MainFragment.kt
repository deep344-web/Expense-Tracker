package com.example.myapplication.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.myapplication.view.viewmodel.MainViewModel
import com.example.myapplication.view.adapter.SpendingAdapter
import com.example.myapplication.databinding.FragmentMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Date


@AndroidEntryPoint
class MainFragment() : Fragment() {

    private lateinit var binding : FragmentMainBinding
    val viewModel by viewModels<MainViewModel>()

    private lateinit var adapter : SpendingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        initializeViews()
        setObservers()
        return binding.root
    }

    private fun initializeViews(){
        binding.signoutBtn.setOnClickListener {
            viewModel.signOutUser()
        }
        binding.btn.setOnClickListener {
            findNavController().navigate(com.example.myapplication.R.id.action_mainFragment_to_addEntryFragment)
        }
        adapter = SpendingAdapter(arrayListOf())
        binding.rv.adapter = adapter

        val builder = MaterialDatePicker.Builder.dateRangePicker()
        binding.dateRangePick.setOnClickListener {
            val picker = builder.build()
            picker.show(activity?.supportFragmentManager!!, picker.toString())
            picker.addOnNegativeButtonClickListener {
            }
            picker.addOnPositiveButtonClickListener {
                Toast.makeText(activity, Date(it.first).toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect{
                    adapter.updateList(it)
                }
            }
        }
    }

}