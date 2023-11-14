package com.example.myapplication.view.repository

import com.example.myapplication.GoogleAuthUiClient
import com.example.myapplication.firestore.CallBackListener
import com.example.myapplication.view.model.UserModel
import com.example.myapplication.view.model.SpendModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class SpendingRepository @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient,
    private val firestore : FirebaseFirestore) {

    private var collectionReference: CollectionReference = firestore.collection(googleAuthUiClient.getSignedInUser()!!.userId)
    private var subCollectionReference : CollectionReference = collectionReference.document("s1").collection("spendings")

    fun storeData(user : UserModel, spend: SpendModel, callBackListener: CallBackListener){
        collectionReference.document("s1").set(user)
        .addOnSuccessListener {
            subCollectionReference.add(spend).addOnSuccessListener{
                callBackListener.onSuccess(null)
            }.addOnFailureListener{
                callBackListener.onFailure(null)

            }
        }.addOnFailureListener{
//            callBackListener.onFailure(it)
        }
    }

    fun retreiveData(callBackListener: CallBackListener){
        subCollectionReference.orderBy("timestamp").get()
        .addOnSuccessListener {
                callBackListener.onSuccess(it)
        }.addOnFailureListener{
                callBackListener.onFailure(it)
        }
    }

    fun retreiveDataWithDateRange(startTime : Long, endTime : Long, callBackListener: CallBackListener){
        subCollectionReference.whereGreaterThanOrEqualTo("timestamp", startTime)
            .whereLessThanOrEqualTo("timestamp", endTime)
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener {
                callBackListener.onSuccess(it)
            }.addOnFailureListener{
                callBackListener.onFailure(it)
            }
    }

    fun getRealtimeUpdates(){
        firestore.collection("Spending").
        document("Spending_1").addSnapshotListener { value, error ->

        }
    }
}