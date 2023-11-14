package com.example.myapplication.view.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.GoogleAuthUiClient
import com.example.myapplication.view.model.SpendModel
import com.example.myapplication.firestore.CallBackListener
import com.example.myapplication.view.repository.SpendingRepository
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient,
    private val repository: SpendingRepository
)
    : ViewModel(), DefaultLifecycleObserver{

    private val _viewState = MutableStateFlow<ArrayList<SpendModel>>(arrayListOf())
    val viewState = _viewState.asStateFlow()

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        repository.retreiveData(object : CallBackListener {
            override fun onSuccess(objects : QuerySnapshot) {
                val list = ArrayList<SpendModel>()
                objects.documents.forEach { document ->
                    document.toObject(SpendModel::class.java)?.let { list.add(it) }
                }
                _viewState.value = list
            }

            override fun onFailure(objects: Any) {
//                _viewState.value = Viewstate.OnSuccess(ActionType.TOAST, "Failure")
            }
        })
    }

    fun signOutUser(){
        viewModelScope.launch(Dispatchers.Default) {
            googleAuthUiClient.signOut()
        }
    }

    fun retrieveDataWithDateRange(startTime : Long, endTime : Long){
        repository.retreiveDataWithDateRange(startTime, endTime, object : CallBackListener {
            override fun onSuccess(objects: QuerySnapshot) {
                val list = ArrayList<SpendModel>()
                objects.documents.forEach { document ->
                    document.toObject(SpendModel::class.java)?.let { list.add(it) }
                }
                _viewState.value = list
            }

            override fun onFailure(objects: Any) {
            }

        })
    }
}