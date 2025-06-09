package com.adytransjaya.data.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object UserPreference {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    private val TOKEN_KEY = stringPreferencesKey("token")
    private val DRIVER_ID_KEY = intPreferencesKey("driver_id")

    suspend fun saveToken(
        context: Context,
        token: String,
    ) {
        context.dataStore.edit { prefs -> prefs[TOKEN_KEY] = token }
    }

    suspend fun getToken(context: Context): String =
        context.dataStore.data
            .map { it[TOKEN_KEY] ?: "" }
            .first()

    suspend fun saveDriverId(
        context: Context,
        id: Int,
    ) {
        context.dataStore.edit { prefs -> prefs[DRIVER_ID_KEY] = id }
    }

    suspend fun getDriverId(context: Context): Int =
        context.dataStore.data
            .map { it[DRIVER_ID_KEY] ?: 0 }
            .first()
}
