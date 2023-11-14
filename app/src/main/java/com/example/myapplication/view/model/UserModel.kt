package com.example.myapplication.view.model

import com.example.myapplication.view.model.SpendModel


class UserModel(){
    var userId : String = ""
    var totalSpend : Long = 0
    var totalIncome : Long = 0

    constructor(userId: String,
                totalSpend : Long,
                totalIncome : Long) : this(){
        this.userId = userId
        this.totalSpend = totalSpend
        this.totalIncome = totalIncome
    }

}
