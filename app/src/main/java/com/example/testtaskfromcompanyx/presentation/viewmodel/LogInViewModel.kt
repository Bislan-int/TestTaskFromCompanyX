package com.example.testtaskfromcompanyx.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskfromcompanyx.domain.usecase.GetUserUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _isUserExists = MutableLiveData<Boolean>()
    val isUserExists: LiveData<Boolean>
        get() = _isUserExists

    private val _isTrueLogIn = MutableLiveData<Boolean>()
    val isTrueLogIn: LiveData<Boolean>
        get() = _isTrueLogIn

    fun validateInput(firstName: String, email: String) {

        viewModelScope.launch {
            val parsName = parsFirstName(firstName)
            val data = getUserUseCase.getUser(parsName)
            Log.d("Name", parsName)
            _isUserExists.value = !(data?.firstName == parsName && data.email == email)
            _isTrueLogIn.value = data?.firstName == parsName && data.email == email
        }

    }

    private fun parsFirstName(inputName: String?): String {
        return inputName?.lowercase()
            ?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }?.trim() ?: ""
    }

    fun resetErrorInput() {
        _isUserExists.value = false
    }
}