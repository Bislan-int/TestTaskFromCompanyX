package com.example.testtaskfromcompanyx.di

import androidx.lifecycle.ViewModel
import com.example.testtaskfromcompanyx.presentation.viewmodel.LogInViewModel
import com.example.testtaskfromcompanyx.presentation.viewmodel.PageOneViewModel
import com.example.testtaskfromcompanyx.presentation.viewmodel.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun signInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    fun logInViewModel(viewModel: LogInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PageOneViewModel::class)
    fun pageOneViewModel(viewModel: PageOneViewModel): ViewModel

}