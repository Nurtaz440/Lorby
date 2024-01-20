package com.example.lorby.repository

import com.example.lorby.data.remote.AuthApi
import com.example.lorby.module.SignInRequest
import com.example.lorby.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor(
    private val localStorage: LocalStorage) {
    fun isFirstLaunch(): Boolean = localStorage.isFirstLaunch

    fun isSinged(): Boolean {
        return true
    }

    fun disableFirstLaunch() {
        localStorage.isFirstLaunch = false
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