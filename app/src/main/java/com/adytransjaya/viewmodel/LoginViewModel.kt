package com.adytransjaya.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var loginSuccess = mutableStateOf(false)
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun login(
        username: String,
        password: String,
    ) {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            try {
                val response = RetrofitInstance.api.login(LoginRequest(username, password))
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        loginSuccess.value = true
                    } else {
                        errorMessage.value = "Response kosong dari server"
                    }
                } else {
                    errorMessage.value = "Login gagal: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage ?: "Terjadi kesalahan"
            }
            isLoading.value = false
        }
    }
}
