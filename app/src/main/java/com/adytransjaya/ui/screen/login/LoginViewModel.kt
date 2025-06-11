package com.adytransjaya.ui.screen.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.Driver
import com.adytransjaya.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
    ) : ViewModel() {
        init {
            Log.d("LoginViewModel", "ViewModel instance created: ${this.hashCode()}")
        }

        var loginSuccess = mutableStateOf(false)
            private set
        var isLoading = mutableStateOf(false)
            private set
        var loginError = mutableStateOf<String?>(null)
            private set
        var token = mutableStateOf<String?>(null)
            private set

        private var _driver = mutableStateOf<Driver?>(null)
        val driver: State<Driver?> = _driver

        fun login(
            username: String,
            password: String,
        ) {
            if (username.isBlank() || password.isBlank()) {
                loginError.value = "Username dan password tidak boleh kosong"
                return
            }

            loginError.value = null
            isLoading.value = true

            viewModelScope.launch {
                try {
                    val response = userRepository.login(username, password)
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body?.token != null) {
                            token.value = body.token
                            userRepository.saveToken(body.token)
                            fetchUser(username)
                        } else {
                            loginError.value = body?.message ?: "Login gagal"
                        }
                    } else {
                        loginError.value = "Response error: ${response.code()}"
                    }
                } catch (e: Exception) {
                    loginError.value = "Error: ${e.localizedMessage}"
                } finally {
                    isLoading.value = false
                }
            }
        }

        private suspend fun fetchUser(username: String) {
            try {
                val response = userRepository.getDriverByUsername(username)

                if (response.isSuccessful) {
                    _driver.value = response.body()?.data
                    loginSuccess.value = true
                    Log.d(
                        "LoginViewModel",
                        "Driver loaded in instance ${this.hashCode()}: ${_driver.value}",
                    )
                } else {
                    loginError.value = "Gagal ambil data user"
                    Log.d("LoginViewModel", "Failed to load driver: ${response.code()}")
                }
            } catch (e: Exception) {
                loginError.value = "Gagal fetch user: ${e.localizedMessage}"
                Log.d("LoginViewModel", "Exception: ${e.localizedMessage}")
            } finally {
                isLoading.value = false
            }
        }
    }
