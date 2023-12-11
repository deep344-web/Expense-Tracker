package com.example.myapplication.view.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.firestore.CallBackListener
import com.example.myapplication.view.model.SpendModel
import com.example.myapplication.view.model.Type
import com.example.myapplication.view.viewmodel.SpendingViewModel
import com.google.firebase.firestore.QuerySnapshot

data class RadioButtonInfo(
    val isChecked : Boolean,
    val label : String
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addEntryScreen(viewModel : SpendingViewModel = hiltViewModel(),
                   navController: NavController){

    var nameFieldState by remember{
        mutableStateOf("")
    }
    var amountFieldState by remember{
        mutableStateOf("")
    }
    var selectedOption by remember {
        mutableStateOf(Type.EXPENSE.value)
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text("Add Entry")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        ){ padding ->
        Box(modifier = Modifier.padding(padding)){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(all = 16.dp)
            ) {
                radioButtons(onClick = {
                    selectedOption = it
                })
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = nameFieldState,
                    onValueChange = {
                        nameFieldState = it
                    },
                    label = { Text(text = "Name") })
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = amountFieldState,
                    onValueChange = {
                        amountFieldState = it
                    },
                    label = { Text(text = "Amount") })
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    viewModel.addEntry(
                        SpendModel(selectedOption, nameFieldState, amountFieldState.toLong()),
                        object : CallBackListener{
                            override fun onSuccess(objects: QuerySnapshot?) {
//                                Toast.makeText(LocalContext.current,
//                                    "Added Successfully!",
//                                    Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                        }

                        override fun onFailure(objects: Any?) {
                            TODO("Not yet implemented")
                        }

                    })
                    navController.popBackStack()
                }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Composable
fun radioButtons(onClick: ((String) -> Unit)){
    var radioButtonsList = remember {
        mutableStateListOf(
            RadioButtonInfo(
                isChecked = true,
                label = Type.EXPENSE.value
            ),
            RadioButtonInfo(
                isChecked = false,
                label = Type.INCOME.value
            )
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        radioButtonsList.forEachIndexed { _, radioButtonInfo ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = radioButtonInfo.label)
                RadioButton(
                    selected = radioButtonInfo.isChecked,
                    onClick = {
                        onClick(radioButtonInfo.label)
                        radioButtonsList.replaceAll{
                            it.copy(
                                isChecked = it.label == radioButtonInfo.label
                            )
                        }
                    })
            }
        }
    }

}
