package com.example.testtaskfromcompanyx.data.db

import com.example.testtaskfromcompanyx.data.Mapper
import com.example.testtaskfromcompanyx.domain.entity.User
import com.example.testtaskfromcompanyx.domain.repository.UserDbRepository
import javax.inject.Inject

class UserDbRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val mapper: Mapper
) : UserDbRepository {

    override suspend fun addUser(user: User) {
        userDao.addUser(mapper.mapEntityToDbModel(user))
    }

    override suspend fun checkUser(key: String): Boolean {
        return userDao.checkUser(key) != null
    }

    override suspend fun getUser(key: String): User? {
        return userDao.getUser(key)?.let { mapper.mapDbModelToEntity(it) }
    }

}