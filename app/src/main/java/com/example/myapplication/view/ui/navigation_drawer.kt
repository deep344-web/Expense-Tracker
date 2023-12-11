package com.example.myapplication.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawerHeader() {
    Box(
       modifier = Modifier
           .padding(all = 12.dp)
           .height(200.dp)
           .fillMaxWidth()
           .background(
               color = Color.Green
           )
    ){
        Text(
            text = "Welcome"
        )
    }
}

@Composable
fun DrawerBody(
    menuItems : List<MenuItemModel>,
    modifier: Modifier
){
    LazyColumn{
        items(menuItems.size) { indx ->
            Row{
                Icon(
                    imageVector = menuItems[indx].icon, 
                    contentDescription = menuItems[indx].contentDesc)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = menuItems[indx].label
                )
            }
        }
    }
}