package com.example.myapplication.view.viewmodel

import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.GoogleAuthUiClient
import com.example.myapplication.view.repository.SpendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {

    fun signIn(){
//        viewModelScope.launch {
//            val intentSender = googleAuthUiClient.signIn()
//            launchHomeIfSignedIn.launch(
//                IntentSenderRequest.Builder(
//                    intentSender ?: return@launch
//                ).build()
//            )
//        }
    }
}