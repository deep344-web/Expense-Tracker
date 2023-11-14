package com.example.myapplication.view.ui

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentAddEntryBinding
import com.example.myapplication.firestore.CallBackListener
import com.example.myapplication.view.model.SpendModel
import com.example.myapplication.view.model.Type
import com.example.myapplication.view.viewmodel.SpendingViewModel
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController


@AndroidEntryPoint
class AddEntryFragment : Fragment() {

    private lateinit var binding : FragmentAddEntryBinding
    private val viewModel by activityViewModels<SpendingViewModel>()

    private val array : ArrayList<Type> = arrayListOf(Type.SPENDING, Type.INCOME)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEntryBinding.inflate(inflater, container, false)
        initializeViews()
        setObservers()
        return binding.root
    }

    private fun initializeViews() {
        binding.addBtn.setOnClickListener {
            val spendModel = SpendModel(
                binding.spinner.selectedItem as Type,
                binding.nameEt.text.toString(),
                binding.amountEt.text.toString().toLong())
            viewModel.addEntry(spendModel, object : CallBackListener{
                override fun onSuccess(objects: QuerySnapshot?) {
                    findNavController().popBackStack()
                }
                override fun onFailure(objects: Any?) {
                    findNavController().popBackStack()
                }
            })
        }

        activity?.let {
            var arrayAdapter = ArrayAdapter(
                it,
                R.layout.simple_spinner_item,
                array
            )

            arrayAdapter.setDropDownViewResource(
                R.layout.simple_spinner_dropdown_item)

            binding.spinner.adapter = arrayAdapter
            binding.spinner.onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?,
                                            arg1 : View,
                                            position : Int,
                                            id : Long) {
                    Toast.makeText(it, array[position].toString(), Toast.LENGTH_LONG).show();
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }

    private fun setObservers(){

    }


}