package com.example.neglectapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalTime

class StoreSessions(private val context: Context) {
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("SessionsStore")
        val START_HOUR_KEY = stringPreferencesKey("start_hour")
        val END_HOUR_KEY = stringPreferencesKey("end_hour")
        val MIN_SESSION = intPreferencesKey("min_session")
        val MAX_SESSION = intPreferencesKey("max_session")

    }

    val getStart: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[START_HOUR_KEY] ?: LocalTime.of(7,30).toString()
        }
    val getEnd: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[END_HOUR_KEY] ?: LocalTime.of(16,0).toString()
        }
    val getMinSession: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[MIN_SESSION] ?: 1
        }
    val getMaxSession: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[MAX_SESSION] ?: 5
        }

    suspend fun saveStart(startHour: String){
        context.dataStore.edit { preferences ->
            preferences[START_HOUR_KEY] = startHour
        }
    }
    suspend fun saveEnd(endHour: String){
        context.dataStore.edit { preferences ->
            preferences[END_HOUR_KEY] = endHour
        }
    }

    suspend fun saveMinSession(min: Int){
        context.dataStore.edit { preferences ->
            preferences[MIN_SESSION] = min
        }
    }
    suspend fun saveMaxSession(min: Int){
        context.dataStore.edit { preferences ->
            preferences[MAX_SESSION] = min
        }
    }
}