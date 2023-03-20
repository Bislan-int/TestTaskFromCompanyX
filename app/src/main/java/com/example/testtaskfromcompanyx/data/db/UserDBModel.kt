package com.example.testtaskfromcompanyx.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDBModel(

    @PrimaryKey
    val firstName: String,
    val lastName: String,
    val email: String
)
