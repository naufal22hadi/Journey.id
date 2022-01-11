package com.example.journeyid.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    val fullName :String,
    val email : String,
    val userName : String,
    val password : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int
)