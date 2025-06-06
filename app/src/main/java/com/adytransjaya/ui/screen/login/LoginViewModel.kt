package com.adytransjaya.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.network.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var message by mutableStateOf("")
    var token by mutableStateOf("")

    fun login() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = RetrofitClient.api.login(LoginRequest(username, password))
                if (response.isSuccessful) {
                    token = response.body()?.token ?: ""
                } else {
                    message = "Login gagal"
                }
            } catch (e: Exception) {
                message = "Error: ${e.message}"
            }
            isLoading = false
        }
    }
}
