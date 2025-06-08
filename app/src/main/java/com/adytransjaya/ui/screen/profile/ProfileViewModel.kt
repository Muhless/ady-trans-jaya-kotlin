package com.adytransjaya.ui.screen.profile

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.DriverProfile
import com.adytransjaya.data.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        application: Application,
    ) : AndroidViewModel(application) {
        var profile by mutableStateOf<DriverProfile?>(null)
        var isLoading by mutableStateOf(false)
        var errorMessage by mutableStateOf("")

        private val context = application.applicationContext

        fun loadProfileById(id: Int) {
            viewModelScope.launch {
                isLoading = true
                try {
                    val response = RetrofitClient.api.getDriverById(id)
                    if (response.isSuccessful) {
                        profile = response.body()
                        errorMessage = ""
                    } else {
                        errorMessage = "Gagal mengambil data profil"
                        profile = null
                    }
                } catch (e: Exception) {
                    errorMessage = "Kesalahan: ${e.localizedMessage}"
                    profile = null
                }
                isLoading = false
            }
        }
    }
