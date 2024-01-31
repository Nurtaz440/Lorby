package com.example.lorby.di

import android.content.Context
import com.example.lorby.data.remote.AuthApi
import com.example.lorby.repository.LocalStorage
import com.example.lorby.utils.insertTokenInterceptor
import com.example.lorby.utils.refreshTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getOkHTTPClient(
        @ApplicationContext context: Context,
        preference: LocalStorage
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

       return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://neobook.online/lorby/authentication/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

}