package com.example.myapplication.view.viewmodel

import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.GoogleAuthUiClient
import com.example.myapplication.SignInResult
import com.example.myapplication.view.model.SpendModel
import com.example.myapplication.view.repository.SpendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SigninViewModel()  : ViewModel() {

    private val _signInState = MutableStateFlow<SignInResult>(SignInResult(null, null))
    val signInState = _signInState.asStateFlow()

    init {

    }

    fun onSignInResult(signInResult: SignInResult){
        _signInState.update {
            it.copy(
                data = signInResult.data,
                errorMessage = signInResult.errorMessage
            )
        }
    }
}