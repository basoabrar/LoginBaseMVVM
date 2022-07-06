package com.example.mogawesubmission.data.reponses

data class DataResponse(
    val token : String?,
    val message: String,
    val `object`: Object,
    val returnValue: String
)