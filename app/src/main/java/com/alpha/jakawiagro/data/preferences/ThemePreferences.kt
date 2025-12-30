package com.alpha.jakawiagro.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

data class ThemeSettings(
    val useSystemTheme: Boolean = true,
    val darkMode: Boolean = false
)

class ThemePreferences(private val context: Context) {

    private object Keys {
        val USE_SYSTEM = booleanPreferencesKey("use_system_theme")
        val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    val settings: Flow<ThemeSettings> = context.dataStore.data.map { prefs ->
        ThemeSettings(
            useSystemTheme = prefs[Keys.USE_SYSTEM] ?: true,
            darkMode = prefs[Keys.DARK_MODE] ?: false
        )
    }

    suspend fun setUseSystemTheme(value: Boolean) {
        context.dataStore.edit { it[Keys.USE_SYSTEM] = value }
    }

    suspend fun setDarkMode(value: Boolean) {
        context.dataStore.edit { it[Keys.DARK_MODE] = value }
    }
}
