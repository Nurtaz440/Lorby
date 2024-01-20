package com.example.lorby.data.remote

import com.example.lorby.module.SignInRequest
import com.example.lorby.module.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/sign-in")
    suspend fun signIn(@Body data: SignInRequest): Response<SignInResponse>

}