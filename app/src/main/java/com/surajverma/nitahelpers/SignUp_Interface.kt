package com.surajverma.nitahelpers

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUp_Interface {
    @POST("engineering/signup")
    suspend fun signup(@Body data: SignUp_Data): Response<Any>

}