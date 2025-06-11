package com.adytransjaya.ui.screen.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.Driver
import com.adytransjaya.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
    ) : ViewModel() {
        var driver = mutableStateOf<Driver?>(null)
            private set

        var isLoading = mutableStateOf(false)
            private set

        var errorMessage = mutableStateOf<String?>(null)
            private set

        fun loadDriver(username: String) {
            viewModelScope.launch {
                isLoading.value = true
                try {
                    val response = userRepository.getDriverByUsername(username)
                    if (response.isSuccessful) {
                        driver.value = response.body()?.driver
                        Log.d("DriverViewModel", "Driver loaded: ${driver.value}")
                        errorMessage.value = null
                    } else {
                        errorMessage.value = "Gagal load driver"
                        Log.e("DriverViewModel", "Response error: ${response.code()}")
                    }
                } catch (e: Exception) {
                    errorMessage.value = e.localizedMessage ?: "Unknown error"
                    Log.e("DriverViewModel", "Exception: ${e.localizedMessage}")
                }
                isLoading.value = false
            }
        }
    }
