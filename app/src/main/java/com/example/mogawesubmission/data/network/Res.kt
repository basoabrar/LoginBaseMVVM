package com.example.mogawesubmission.data.network

import com.example.mogawesubmission.data.reponses.LoginResponse
import okhttp3.ResponseBody

sealed class Res<out T> {
    data class Succes<out T>(val value: T) : Res<T>()
    data class Fail(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Res<Nothing>()

    object Loading : Res<Nothing>()
}