package com.example.mogawesubmission.data.network

import com.example.mogawesubmission.data.reponses.LoginResponse
import retrofit2.http.Body

import retrofit2.http.POST

 interface AuthApi {

    @POST("your endpoint here")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : LoginResponse

}