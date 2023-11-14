package com.example.myapplication.firestore

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import java.util.Objects

interface CallBackListener {
    fun onSuccess(objects: QuerySnapshot?)
    fun onFailure(objects: Any?)
}