package com.example.testtaskfromcompanyx.presentation

import android.app.Application
import com.example.testtaskfromcompanyx.di.DaggerApplicationComponent

class TestTaskApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}