package com.adytransjaya.ui.screen.login

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.UserPreference
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        appplication: Application,
    ) : AndroidViewModel(appplication) {
        var username by mutableStateOf("")
        var password by mutableStateOf("")
        var isLoading by mutableStateOf(false)
        var message by mutableStateOf("")
        var token by mutableStateOf("")
        var driverId by mutableStateOf(0)

        private val context = application.applicationContext

        fun login() {
            viewModelScope.launch {
                isLoading = true
                try {
                    val response = RetrofitClient.api.login(LoginRequest(username, password))
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        token = responseBody?.token ?: ""
                        driverId = responseBody?.driverId ?: 0
                        message = ""

                        // Simpan token dan driverId ke DataStore (buat nanti)
                        UserPreference.saveToken(context, token)
                        UserPreference.saveDriverId(context, driverId)

                        message = ""
                    } else {
                        val errorBody = response.errorBody()?.string()
                        message =
                            when (response.code()) {
                                400 -> "Input tidak valid. Mohon cek kembali data Anda."
                                401 -> "Username atau password salah."
                                else -> "Login gagal (${response.code()}): ${errorBody ?: "Kesalahan tidak diketahui"}"
                            }
                    }
                } catch (e: Exception) {
                    message = "Tidak dapat terhubung ke server. Pastikan koneksi internet stabil."
                }
                isLoading = false
            }
        }

        suspend fun loadSavedData() {
            token = UserPreference.getToken(context)
            driverId = UserPreference.getDriverId(context)
        }
    }
