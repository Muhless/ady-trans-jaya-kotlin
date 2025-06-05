package com.adytransjaya.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var loginSuccess by mutableStateOf(false)

    fun login(
        username: String,
        password: String,
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitInstance.api.login(LoginRequest(username, password))
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        // simpan token, dll
                        loginSuccess = true
                    } else {
                        errorMessage = "Response kosong"
                    }
                } else {
                    errorMessage = "Login gagal: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Terjadi kesalahan"
            }
            isLoading = false
        }
    }
}
