package com.adytransjaya.ui.screen.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val apiService: ApiService,
    ) : ViewModel() {
        var isLoading = mutableStateOf(false)
            private set
        var loginError = mutableStateOf<String?>(null)
            private set
        var token = mutableStateOf<String?>(null)
            private set

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
                    val response = apiService.login(LoginRequest(username, password))
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body?.token != null) {
                            token.value = body.token
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
    }
