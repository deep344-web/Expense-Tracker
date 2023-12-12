package com.example.myapplication.view.ui

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItemModel(
    val id : String,
    val label : String,
    val contentDesc : String,
    val selectedIcon : ImageVector,
    val unSelectedIcon : ImageVector
)

enum class MenuIds(val value : String){
    HOME("home"),
    ADD("add"),
    SETTINGS("settings"),
    SIGN_OUT("sign_out")
}
