package com.example.lorby.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorby.R
import com.example.lorby.repository.AuthRepository
import com.example.lorby.utils.NavigationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel@Inject constructor(
    private val authRepository: AuthRepository,
    private val navigationHelper: NavigationHelper
) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(1000)
            when {
//                authRepository.isFirstLaunch() -> {
//                    navigationHelper.navigateTo(SplashScreenDirections.actionSplashScreenToTermsScreen())
//                }
                authRepository.isSinged() -> {
                    navigationHelper.navigateTo(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
                }
//                else -> { /*Login*/
//                }
            }
        }
    }
}