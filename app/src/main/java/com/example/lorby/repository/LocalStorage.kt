package com.example.lorby.repository

import android.content.Context
import com.example.lorby.utils.SharedPreferenceHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalStorage @Inject constructor(
    @ApplicationContext context: Context
) : SharedPreferenceHelper(context) {
    var isFirstLaunch: Boolean by booleans(true)
    var token: String by strings()
    var getRefreshToken : String by strings()
    var getAccessToken : String by strings()

}