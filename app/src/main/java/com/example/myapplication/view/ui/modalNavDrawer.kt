package com.example.myapplication.view.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun modalNavigationDrawer(
    items : List<MenuItemModel>,
    onNavigationIconClick : (() -> Unit)
){
    var drawerState = rememberDrawerState(
        DrawerValue.Closed
    )

    var selectedItemIndx by rememberSaveable {
        mutableIntStateOf(0)
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                items.forEachIndexed{ index, item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.contentDesc
                            )
                        },
                        label = {
                            Text(text = item.label)
                        },
                        selected = index == selectedItemIndx,
                        onClick = {
                            selectedItemIndx = index
                            //close drawer sheet
                        })
                }
            }},
         drawerState = drawerState) {

    }
}