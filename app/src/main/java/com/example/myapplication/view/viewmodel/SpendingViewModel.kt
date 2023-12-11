package com.example.myapplication.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.GoogleAuthUiClient
import com.example.myapplication.view.model.UserModel
import com.example.myapplication.view.model.SpendModel
import com.example.myapplication.firestore.CallBackListener
import com.example.myapplication.view.repository.SpendingRepository
import com.example.myapplication.view.view_state.Viewstate
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SpendingViewModel @Inject constructor(
    private val repository: SpendingRepository,
    private val googleAuthUiClient: GoogleAuthUiClient) : ViewModel() {

    private val _viewState = MutableStateFlow<Viewstate>(Viewstate.InitState)
    val viewState = _viewState.asStateFlow()

    fun addEntry(spendModel : SpendModel, callBackListener: CallBackListener) {

        val userModel = UserModel(googleAuthUiClient.getSignedInUser()!!.userId, 0, 0)
        repository.storeData(userModel, spendModel, callBackListener)
    }

//    fun getData(){
//        repository.retreiveData(object : CallBackListener{
//            override fun onSuccess(objects: Any) {
//                _viewState.value = Viewstate.OnSuccess(ActionType.UPDATE,  list = objects)
//            }
//
//            override fun onFailure(objects: Any) {
//                _viewState.value = Viewstate.OnSuccess(ActionType.TOAST, "Failure")
//            }
//        })
//    }

    fun getRealtimeUpdates(){

    }

}