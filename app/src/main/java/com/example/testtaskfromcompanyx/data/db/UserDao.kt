package com.example.testtaskfromcompanyx.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserDBModel)

    @Query("SELECT * FROM users WHERE firstName=:firstNameUser LIMIT 1")
    suspend fun checkUser(firstNameUser: String): UserDBModel?

    @Query("SELECT * FROM users WHERE firstName=:key LIMIT 1")
    suspend fun getUser(key: String): UserDBModel?

}