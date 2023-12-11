package com.example.myapplication.view.ui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.R
import com.example.myapplication.view.model.Screen


@Composable
fun splashScreen(navController : NavController){
//    var isPlaying by remember {
//        mutableStateOf(true)
//    }
//
//    var speed by remember {
//        mutableStateOf(1f)
//    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 1,
//        isPlaying = isPlaying,
        speed = 1.5f,
        restartOnPlay = false
    )

    LottieAnimation(
        composition,
        progress,
        modifier = Modifier.size(400.dp)
    )
    if (progress == 1.0f) {
        navController.navigate(Screen.SignInScreen.route)
    }

}
