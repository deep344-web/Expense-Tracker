package com.example.myapplication.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.GoogleAuthUiClient
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSigninBinding
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SigninFragment : Fragment() {
    private lateinit var binding : FragmentSigninBinding
    private val googleAuthUiClient by lazy {
        activity?.let {
            GoogleAuthUiClient(
                it.applicationContext,
                Identity.getSignInClient(it.applicationContext)
            )
        }
    }

    private val launchHomeIfSignedIn = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            lifecycleScope.launch {
                result.data?.let {
                    googleAuthUiClient?.signInWithIntent(it)
//                    val intent = Intent(activity, MainActivity::class.java)
//                    startActivity(intent)
                    findNavController().navigate(R.id.action_signinFragment_to_mainFragment)
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        binding.signinBtn.setOnClickListener{
            lifecycleScope.launch {
                val intentSender = googleAuthUiClient?.signIn()
                launchHomeIfSignedIn.launch(
                    IntentSenderRequest.Builder(
                        intentSender ?: return@launch
                    ).build())
            }
        }
        return binding.root
    }


}