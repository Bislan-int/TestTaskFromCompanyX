package com.example.testtaskfromcompanyx.domain.repository

//import androidx.lifecycle.LiveData
import com.example.testtaskfromcompanyx.domain.entity.User

interface UserDbRepository {

    suspend fun addUser(user: User)

    suspend fun checkUser(key: String): Boolean

    suspend fun getUser(key: String): User?
}