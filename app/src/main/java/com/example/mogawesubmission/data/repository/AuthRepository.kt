package com.example.mogawesubmission.data.repository

import com.example.mogawesubmission.data.UserPreference
import com.example.mogawesubmission.data.network.AuthApi
import com.example.mogawesubmission.data.network.LoginRequest

class AuthRepository(
    private val api: AuthApi,
    private val preference: UserPreference
) : BaseRepository() {


    suspend fun login(loginRequest: LoginRequest
    ) = SafeApiCall { api.login(loginRequest) }




    suspend fun saveAuthToken(token : String){
        preference.saveAuthToken(token)
    }

}