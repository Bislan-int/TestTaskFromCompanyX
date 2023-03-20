package com.example.testtaskfromcompanyx.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.testtaskfromcompanyx.domain.usecase.AddUserUseCase
import com.example.testtaskfromcompanyx.domain.usecase.CheckUserUseCase
import com.example.testtaskfromcompanyx.domain.entity.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val checkUserUseCase: CheckUserUseCase
) : ViewModel() {

//    private val repository = UserDbRepositoryImpl(application)


    private val _errorInputFirstName = MutableLiveData<Boolean>()
    val errorInputFirstName: LiveData<Boolean>
        get() = _errorInputFirstName

    private val _errorInputLastName = MutableLiveData<Boolean>()
    val errorInputLastName: LiveData<Boolean>
        get() = _errorInputLastName

    private val _errorInputEmail = MutableLiveData<Boolean>()
    val errorInputEmail: LiveData<Boolean>
        get() = _errorInputEmail

    private val _isUserExists = MutableLiveData<Boolean>()
    val isUserExists: LiveData<Boolean>
        get() = _isUserExists

    private val _validInputValues = MutableLiveData<Boolean>()
    val validInputValues: LiveData<Boolean>
        get() = _validInputValues

    fun checkUser(firstName: String) {
        viewModelScope.launch {
            Log.d("checkUser1", "${checkUserUseCase.checkUser(firstName)}")
            Log.d("isUserExistsBefore", "${isUserExists.value}")
            _isUserExists.value = checkUserUseCase.checkUser(firstName)
            Log.d("isUserExistsAfter", "${isUserExists.value}")
        }
    }

    fun addUser(inputFirstName: String?, inputLastName: String?, inputEmail: String?) {
        val firstName = parsNames(inputFirstName)
        val lastName = parsNames(inputLastName)
        val email = parsEmail(inputEmail)
        val fieldsValid = validateInput(firstName = firstName, lastName = lastName, email = email)
        checkUser(firstName)
        viewModelScope.launch {
            delay(500)
            if (fieldsValid) {
                val user = User(
                    firstName = firstName,
                    lastName = lastName,
                    email = email
                )
                addUserUseCase.addUser(user)
            }
            Log.d("fieldsValidVM", "${_isUserExists.value}")
            _validInputValues.value = fieldsValid && _isUserExists.value == false
        }
    }

    private fun parsNames(inputName: String?): String {
        return inputName?.lowercase()
            ?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }?.trim() ?: ""
    }

    //Доделать проверку на количество букв до и после "@email.". Минимум по одной буквы с каждой стороны.
    private fun parsEmail(inputEmail: String?): String {
        return if (
            inputEmail?.length!! >= 8
            && (inputEmail.contains("@mail.", ignoreCase = true)
                    || inputEmail.contains("@gmail.", ignoreCase = true)
                    || inputEmail.contains("@inbox.", ignoreCase = true))
        ) inputEmail.lowercase().trim() else ""
    }

    private fun validateInput(firstName: String, lastName: String, email: String): Boolean {
        var result = true
        if (firstName.isBlank()) {
            _errorInputFirstName.value = true
            result = false
        }
        if (lastName.isBlank()) {
            _errorInputLastName.value = true
            result = false
        }
        if (email.isBlank()) {
            _errorInputEmail.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputFirstName() {
        _errorInputFirstName.value = false
    }

    fun resetErrorInputLastName() {
        _errorInputLastName.value = false
    }

    fun resetErrorInputEmail() {
        _errorInputEmail.value = false
    }
}