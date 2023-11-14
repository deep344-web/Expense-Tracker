package com.example.myapplication

import java.io.Serializable

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
) : Serializable

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)
