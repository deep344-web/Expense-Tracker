package com.example.myapplication.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = teal80,
    onPrimary = teal20,
    primaryContainer = teal30,
    onPrimaryContainer = teal90,
    inversePrimary = teal40,
    secondary = gray80,
    onSecondary = gray20,
    surface = teal30
)

private val LightColorScheme = lightColorScheme(
    primary = teal40,
    onPrimary = Color.White,
    primaryContainer = teal90,
    onPrimaryContainer = teal10,
    inversePrimary = teal80,
    secondary = gray40,
    onSecondary = Color.White,
    surface = teal90

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val useDynamicColorScheme = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
//        useDynamicColorScheme && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
//        useDynamicColorScheme && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}