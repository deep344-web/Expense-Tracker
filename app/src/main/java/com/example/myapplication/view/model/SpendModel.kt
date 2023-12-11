package com.example.myapplication.view.model

import com.google.firebase.firestore.ServerTimestamp
import java.time.Instant
import java.util.Date
import java.util.TimeZone


class SpendModel() {
    var type : String = Type.EXPENSE.value
    var name : String = ""
    var amount : Long = 0
    var timestamp: Long = Instant.now().toEpochMilli()

    constructor(type: String, name : String, amount : Long) : this() {
        this.type = type
        this.name = name
        this.amount = amount
    }
}