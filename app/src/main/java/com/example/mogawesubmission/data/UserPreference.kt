package com.example.mogawesubmission.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


const val DataStore_Name = "KCAW"
val Context.myDataStore: DataStore<Preferences> by preferencesDataStore(name = DataStore_Name)

class UserPreference(
    private val context: Context
) {
    val authToken: Flow<String?>
        get() = context.myDataStore.data.map {
            it[TOKEN_AUTH]
        }

    suspend fun saveAuthToken(authToken: String) {
        context.myDataStore.edit { preference ->
            preference[TOKEN_AUTH] = authToken
        }
    }

    companion object {
        private val TOKEN_AUTH = stringPreferencesKey("token")
    }

    suspend fun clearToken(){
        context.myDataStore.edit { preferences ->
            preferences.clear()
        }
    }

}