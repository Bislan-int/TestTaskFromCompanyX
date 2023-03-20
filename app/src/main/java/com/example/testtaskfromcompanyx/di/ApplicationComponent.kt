package com.example.testtaskfromcompanyx.di

import android.app.Application
import com.example.testtaskfromcompanyx.presentation.fragment.LogInFragment
import com.example.testtaskfromcompanyx.presentation.fragment.PageOneFragment
import com.example.testtaskfromcompanyx.presentation.fragment.SignInFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(signInFragment: SignInFragment)
    fun inject(logInFragment: LogInFragment)
    fun inject(pageOneFragment: PageOneFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}