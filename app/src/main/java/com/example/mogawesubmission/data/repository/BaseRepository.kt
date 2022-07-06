package com.example.mogawesubmission.data.repository

import com.example.mogawesubmission.data.network.Res
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {


    suspend fun <T> SafeApiCall(
        apiCall : suspend () -> T
    ) : Res<T>{
        return  withContext(Dispatchers.IO){
            try {
                Res.Succes(apiCall.invoke())
            }catch (throwable : Throwable){
                when(throwable){
                    is HttpException -> {
                        Res.Fail(true , throwable.code() , throwable.response()?.errorBody())
                    }
                    else -> {
                        Res.Fail(true, null , null)
                    }
                }
            }
        }
    }



}