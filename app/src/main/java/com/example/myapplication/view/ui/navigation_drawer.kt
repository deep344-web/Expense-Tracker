package com.example.myapplication.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.UserData

@Composable
fun DrawerHeader(signInUser: UserData?) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(all = 8.dp)
    ){
        Column {
            Text(
                text = "Welcome"
            )
            Text(
                text = signInUser?.username.toString()
            )
        }
    }
}