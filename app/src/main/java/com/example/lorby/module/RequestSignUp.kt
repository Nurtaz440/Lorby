package com.example.lorby.module

import com.google.gson.annotations.SerializedName

data class RequestSignUp(

	@field:SerializedName("email")
	val email: String? = null,
	
	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("password_confirm")
	val passwordConfirm: String? = null


)
