package com.example.lorby.ui.signUp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorby.repository.AuthRepository
import com.example.lorby.utils.NavigationHelper
import com.example.lorby.utils.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navigationHelper: NavigationHelper
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()


    fun regstration(
        email: String,
        username: String,
        password: String,
        password_confirm: String) {


        _loading.value = true

        authRepository.signUp(
            email = email,
            username = username,
            password = password,
            password_confirm = password_confirm
        )
            .onEach {


                when (it) {
                    is ResultData.Success -> {
                        navigationHelper.navigateTo(
                            SignUpFragmentDirections.actionSignUpFragmentToVerifyFragment())
                        Log.d("Tagooo", "login: ${it.data}")
                        _loading.value = false
                    }  //Next screen
                    is ResultData.Error -> {
                        _message.emit(it.message)
                        _loading.value = false
                        Log.d("Tagooo", "login: ${it.message}")
                    }//error
                }
            }
            .launchIn(viewModelScope)
    }
}