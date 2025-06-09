package com.adytransjaya.data.preference

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TokenPreferences(
    private val sharedPreferences: SharedPreferences,
) {
    companion object {
        private const val KEY_TOKEN = "key_token"
    }

    private val tokenFlow = MutableStateFlow(getTokenFromPrefs())

    fun getTokenFlow(): Flow<String?> = tokenFlow

    suspend fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
        tokenFlow.value = token
    }

    private fun getTokenFromPrefs(): String? = sharedPreferences.getString(KEY_TOKEN, null)
}
