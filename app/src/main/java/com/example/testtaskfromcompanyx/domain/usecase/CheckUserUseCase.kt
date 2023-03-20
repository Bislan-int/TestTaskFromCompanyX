package com.example.testtaskfromcompanyx.domain.usecase

import android.util.Log
import com.example.testtaskfromcompanyx.domain.repository.UserDbRepository
import javax.inject.Inject

class CheckUserUseCase @Inject constructor(
    private val userRepository: UserDbRepository
) {

    suspend fun checkUser(firstNameUser: String): Boolean {
        Log.d("GetUserUseCase", "${userRepository.checkUser(firstNameUser)}")
        return userRepository.checkUser(firstNameUser)
    }
}