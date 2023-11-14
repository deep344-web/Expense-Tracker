package com.example.myapplication.view.view_state

sealed class Viewstate {

    data class OnSuccess(private val actionType : ActionType,
                         private val message : String? = null,
                         private val list : ArrayList<Any>? = null) : Viewstate()

    data class OnError(private val actionType : ActionType,
                       private val message : String) : Viewstate()

    object InitState : Viewstate()
}