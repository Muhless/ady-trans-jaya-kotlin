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

    private val KEY_TOKEN = stringPreferencesKey("token")
    private val KEY_DRIVER_ID = intPreferencesKey("driver_id")

    suspend fun saveToken(
        context: Context,
        token: String,
    ) {
        context.dataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
        }
    }

    suspend fun getToken(context: Context): String? =
        context.dataStore.data
            .map { prefs -> prefs[KEY_TOKEN] }
            .first()

    suspend fun saveDriverId(
        context: Context,
        id: Int,
    ) {
        context.dataStore.edit { prefs ->
            prefs[KEY_DRIVER_ID] = id
        }
    }

    suspend fun getDriverId(context: Context): Int =
        context.dataStore.data
            .map { prefs -> prefs[KEY_DRIVER_ID] ?: 0 }
            .first()

    suspend fun clear(context: Context) {
        context.dataStore.edit { it.clear() }
    }
}
