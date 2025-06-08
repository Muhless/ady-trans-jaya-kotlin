package com.adytransjaya.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object UserPreference {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")
    private val USERNAME_KEY = stringPreferencesKey("username")

    suspend fun saveUsername(
        context: Context,
        username: String,
    ) {
        context.dataStore.edit { prefs -> prefs[USERNAME_KEY] = username }
    }

    suspend fun getUsername(context: Context): String =
        context.dataStore.data
            .map { prefs -> prefs[USERNAME_KEY] ?: "" }
            .first()

    suspend fun clear(context: Context) {
        context.dataStore.edit { it.clear() }
    }
}
