package com.example.lorby.utils

import com.example.lorby.module.UpdateTokenResponse
import com.example.lorby.repository.LocalStorage
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import org.json.JSONObject

fun insertTokenInterceptor(
    preference: LocalStorage
) = Interceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader("authorization", "Bearer ${preference.getAccessToken}").build()
    chain.proceed(request)
}

fun refreshTokenInterceptor(
    preference: LocalStorage
) = Interceptor { chain ->
    val request = chain.request()
    val response = chain.proceed(request)

    if (response.code == 401) {
        response.close()

        val data = JSONObject()
        data.put("refresh-token", preference.getRefreshToken)
        val body =
            RequestBody.create("application/json; charset=utf-8".toMediaType(), data.toString())

        val requestRefresh = request.newBuilder()
            .method("POST", body)
            .url("https://neobook.online/lorby/auth/update-token")
            .build()

        val responseRefresh = chain.proceed(requestRefresh)
        if (responseRefresh.code == 401) {
            return@Interceptor responseRefresh

            // refresh token ham eskirdi login screen navigate
        }

//        if (responseRefresh.code() == 200) {
//            responseRefresh.body()?.let {
//                val refreshData = Gson().fromJson(it.string(), UpdateTokenResponse::class.java)
//                preference.getRefreshToken=refreshData.refreshToken
//                preference.getAccessToken=refreshData.accessToken
//            }
//
//            responseRefresh.close()
//
//            val requestSecond = request.newBuilder()
//                .removeHeader("token")
//                //.addHeader("token", preference.getAccessToken())
//                .build()
//            return@Interceptor chain.proceed(requestSecond)
//        }
    }

    return@Interceptor response
}