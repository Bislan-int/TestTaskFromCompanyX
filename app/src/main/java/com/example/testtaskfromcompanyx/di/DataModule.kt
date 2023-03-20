package com.example.testtaskfromcompanyx.di

import android.app.Application
import com.example.testtaskfromcompanyx.data.db.AppDatabase
import com.example.testtaskfromcompanyx.data.db.UserDao
import com.example.testtaskfromcompanyx.data.db.UserDbRepositoryImpl
import com.example.testtaskfromcompanyx.data.network.Api
import com.example.testtaskfromcompanyx.data.network.NetworkRepositoryImpl
import com.example.testtaskfromcompanyx.domain.repository.NetworkRepository
import com.example.testtaskfromcompanyx.domain.repository.UserDbRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindUserDbRepository(impl: UserDbRepositoryImpl): UserDbRepository

    @ApplicationScope
    @Binds
    fun bindNetworkRepository(impl: NetworkRepositoryImpl): NetworkRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideUserDao(
            application: Application
        ): UserDao {
            return AppDatabase.getInstance(application).userDao()
        }

        @ApplicationScope
        @Provides
        fun provideApi(): Api {
            return Api.create()
        }
    }
}