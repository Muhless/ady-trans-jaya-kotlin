package com.adytransjaya.ui.screen.login

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.Driver
import com.adytransjaya.data.model.LoginResponse
import com.adytransjaya.data.preference.UserPreference
import com.adytransjaya.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        application: Application,
        private val userRepository: UserRepository,
    ) : AndroidViewModel(application) {
        private val context = application.applicationContext

        val username = mutableStateOf("")
        val password = mutableStateOf("")
        val token = mutableStateOf("")
        val loginError = mutableStateOf<String?>(null)
        val isLoading = mutableStateOf(false)
        val loginSuccess = mutableStateOf(false)

        val driver = mutableStateOf<Driver?>(null)

        fun login(
            username: String,
            password: String,
        ) {
            if (!validateInput(username, password)) return

            loginError.value = null
            isLoading.value = true

            viewModelScope.launch {
                try {
                    val response = userRepository.login(username, password)
                    handleLoginResponse(response)
                } catch (e: Exception) {
                    loginError.value = "Terjadi kesalahan: ${e.localizedMessage}"
                } finally {
                    isLoading.value = false
                }
            }
        }

        private fun validateInput(
            username: String,
            password: String,
        ): Boolean =
            if (username.isBlank() || password.isBlank()) {
                loginError.value = "Username dan password tidak boleh kosong"
                false
            } else {
                true
            }

        private suspend fun handleLoginResponse(response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("LoginViewModel", "Driver response: $body")
                if (body?.token != null) {
                    token.value = body.token
                    saveUserSession(body)
                    loginSuccess.value = true
                } else {
                    loginError.value = body?.message ?: "Login gagal"
                }
            } else {
                loginError.value = "Login gagal: ${response.code()}"
            }
        }

        private suspend fun saveUserSession(body: LoginResponse) {
            body.token?.let { token ->
                UserPreference.saveToken(context, token)
            }
            body.driver?.let { driverData ->
                UserPreference.saveDriverId(context, driverData.id)
                driver.value = driverData
            }
        }

//        private fun fetchUser(username: String) {
//            viewModelScope.launch {
//                try {
//                    val response = userRepository.getDriverByUsername(username)
//                    if (response.isSuccessful) {
//                        val body = response.body()
//                        val fetchedDriver = body?.driver
//                        if (fetchedDriver != null) {
//                            driver.value = fetchedDriver
//                            UserPreference.saveDriverId(context, fetchedDriver.id)
//                            loginSuccess.value = true
//                        } else {
//                            loginError.value = "Data driver tidak ditemukan"
//                        }
//                    } else {
//                        loginError.value = "Gagal mengambil data user: ${response.code()}"
//                    }
//                } catch (e: Exception) {
//                    loginError.value = "Terjadi kesalahan: ${e.localizedMessage}"
//                }
//            }
//        }
    }
