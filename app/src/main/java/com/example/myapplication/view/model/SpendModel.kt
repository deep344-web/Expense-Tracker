package com.example.myapplication.view.model

import com.google.firebase.firestore.ServerTimestamp
import java.time.Instant
import java.util.Date
import java.util.TimeZone


class SpendModel() {
    var type : Type = Type.SPENDING
    var name : String = ""
    var amount : Long = 0
    var timestamp: Long = Instant.now().toEpochMilli()
    var timeZone : String = TimeZone.getDefault().id


    constructor(type: Type, name : String, amount : Long) : this() {
        this.type = type
        this.name = name
        this.amount = amount
    }
}