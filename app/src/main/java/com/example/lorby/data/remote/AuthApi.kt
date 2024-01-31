package com.example.lorby.data.remote

import com.example.lorby.module.RequestSignUp
import com.example.lorby.module.SignInRequest
import com.example.lorby.module.SignInResponse
import com.example.lorby.module.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @POST("authentication/login")
    suspend fun signIn(@Body data: SignInRequest): Response<SignInResponse>
   
    @POST("register/")
    suspend fun signUp(@Body data: RequestSignUp): Response<SignUpResponse>
}