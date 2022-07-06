package com.example.mogawesubmission.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mogawesubmission.data.network.LoginRequest
import com.example.mogawesubmission.data.network.Res
import com.example.mogawesubmission.data.reponses.LoginResponse
import com.example.mogawesubmission.data.repository.AuthRepository
import com.example.mogawesubmission.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.security.MessageDigest

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Res<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Res<LoginResponse>>
        get() = _loginResponse

    private var _returnValue : String = ""
    val returnValue : String
    get() = _returnValue


    fun login(loginRequest: LoginRequest) = viewModelScope.launch {
        _loginResponse.value = repository.login(loginRequest)

    }


    suspend fun saveReturnValue (returnValue : String) {
        _returnValue = returnValue
    }


    suspend fun saveAuthToken (token : String) {
        repository.saveAuthToken(token)
    }

}