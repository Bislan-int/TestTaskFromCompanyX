package com.example.testtaskfromcompanyx.domain.usecase

import com.example.testtaskfromcompanyx.domain.entity.User
import com.example.testtaskfromcompanyx.domain.repository.UserDbRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserDbRepository
) {

    suspend fun getUser(key: String): User? {
        return repository.getUser(key)
    }
}