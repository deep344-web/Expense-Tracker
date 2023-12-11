package com.example.myapplication.view.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.GoogleAuthUiClient
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.view.model.Screen
import com.example.myapplication.view.viewmodel.SigninViewModel
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavHostActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            applicationContext,
            Identity.getSignInClient( applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
                        composable(route = Screen.SplashScreen.route) {
                            splashScreen(navController)
                        }
                        composable(route = Screen.SignInScreen.route){
                            val viewModel = viewModel<SigninViewModel>()

                            val launchHomeIfSignedIn = rememberLauncherForActivityResult(
                                ActivityResultContracts.StartIntentSenderForResult()) { result ->
                                if (result.resultCode == AppCompatActivity.RESULT_OK) { 
                                    lifecycleScope.launch {
                                        result.data?.let {
                                            val signInResult = googleAuthUiClient.signInWithIntent(it)
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            }
                            LaunchedEffect(key1 = viewModel.signInState){
                                googleAuthUiClient.getSignedInUser()?.let {
                                    Toast.makeText(applicationContext,
                                        "Signed in successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.popBackStack()
                                    navController.navigate(Screen.HomeScreen.route)
                                }
                            }
                            BackHandler(true) {
                                Log.i("LOG_TAG", "Clicked back")
                            }
                            signInScreen(
                                onSignInBtnClick = {
                                    lifecycleScope.launch {
                                        val intentSender = googleAuthUiClient.signIn()
                                        launchHomeIfSignedIn.launch(
                                            IntentSenderRequest.Builder(
                                                intentSender ?: return@launch
                                            ).build())
                                    }
                                })
                        }
                        composable(route = Screen.HomeScreen.route){
                            BackHandler(true) {
                                Log.i("LOG_TAG", "Clicked back")
                            }
                            homePage( navController = navController,
                                onLogOutClick = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        navController.popBackStack()
                                        navController.navigate(Screen.SignInScreen.route)
                                    }
                                },
                                items = listOf(
                                    MenuItemModel("home", "Home", "home", Icons.Filled.Home),
                                    MenuItemModel("add", "Add", "add", Icons.Filled.AddCircle),
                                    MenuItemModel("settings", "Settings", "settings", Icons.Filled.Settings),
                                    MenuItemModel("logout", "Sign out", "logout", Icons.Filled.ExitToApp),
                                    )
                            )
                        }
                        composable(route = Screen.AddEntryScreen.route){
                            addEntryScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

