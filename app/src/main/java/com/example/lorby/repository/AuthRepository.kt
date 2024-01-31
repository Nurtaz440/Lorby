package com.example.lorby.repository

import android.util.Log
import android.util.Log.VERBOSE
import com.example.lorby.data.remote.AuthApi
import com.example.lorby.module.RequestSignUp
import com.example.lorby.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor(
    private val localStorage: LocalStorage,
    private val authApi: AuthApi
) {

    fun isSinged(): Boolean {
        return true
    }


    fun signUp(
        email: String,
        username: String,
        password: String,
        password_confirm: String
    ): Flow<ResultData<String>> = flow {
        val request = RequestSignUp(
            email = email,
            username = username,
            password = password,
            passwordConfirm = password_confirm
        )
        val response = authApi.signUp(request)
        Log.d("AAA", response.code().toString())


        if (response.isSuccessful) {
            val responseBody = response.body()
            val token: String = responseBody?.message.toString()
            Log.d("ResultData", token.toString())

            emit(ResultData.Success(token))
        } else {
            emit(ResultData.Error("Xatolik sodir bo'ldi"))
        }
    }
        .flowOn(Dispatchers.IO)
        .catch {
            emit(ResultData.Error("Xatolik sodir bo'ldi"))
        }


//    fun login(phone: String, password: String): Flow<ResultData<String>> = flow {
//        val request = SignInRequest(phone, password)
//        val response = authApi.signIn(request)
//        if (response.isSuccessful) {
//            val responseBody = response.body()
//            val token: String = responseBody?.token.toString()
//            emit(ResultData.Success(token))
//        } else {
//            emit(ResultData.Error("Xatolik sodir bo'ldi"))
//        }
//    }
//        .flowOn(Dispatchers.IO)
//        .catch {
//            emit(ResultData.Error("Xatolik sodir bo'ldi"))
//        }

}