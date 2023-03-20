package com.example.testtaskfromcompanyx.domain.usecase

import com.example.testtaskfromcompanyx.domain.entity.User
import com.example.testtaskfromcompanyx.domain.repository.UserDbRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserDbRepository
) {

    suspend fun addUser(user: User) {
        userRepository.addUser(user)
    }
}