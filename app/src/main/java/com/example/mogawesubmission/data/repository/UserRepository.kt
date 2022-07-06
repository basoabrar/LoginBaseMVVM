package com.example.mogawesubmission.data.repository

import com.example.mogawesubmission.data.UserPreference
import com.example.mogawesubmission.data.network.AuthApi
import com.example.mogawesubmission.data.network.LoginRequest
import com.example.mogawesubmission.data.network.UserApi

class UserRepository(
    private val api: UserApi

) : BaseRepository() {

    suspend fun getUser() = SafeApiCall {
        api.getUserInfo()
    }


}