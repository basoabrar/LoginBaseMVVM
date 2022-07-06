package com.example.mogawesubmission.data.network

import com.example.mogawesubmission.data.reponses.DataResponse

import retrofit2.http.POST

interface UserApi {

    @POST("v2/getProfile")
    suspend fun getUserInfo(): DataResponse
}