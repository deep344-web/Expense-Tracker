package com.example.myapplication.view.model

sealed class Screen(val route : String){
    object SplashScreen : Screen(route = "splash_screen")

    object SignInScreen : Screen(route = "sign_in_screen")
    object HomeScreen : Screen(route = "home_screen")
    object AddEntryScreen : Screen(route = "add_entry_screen")
}
