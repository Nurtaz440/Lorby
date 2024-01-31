package com.example.lorby.utils

sealed interface  ResultData <out T>{
    class Success<T>(val data:T):ResultData<T>
    class Error(val message : String):ResultData<Nothing>
    //https://neobook.online/lorby/authentication/
}