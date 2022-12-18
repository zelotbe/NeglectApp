package com.example.neglectapp.data.datastore

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.neglectapp.MainApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalTime

@OptIn(ExperimentalAnimationApi::class)
class LocalDataStore : IDataStore {
    companion object{
        //STIMULA KEYS
        val VIBRATION_STIMULA_KEY = booleanPreferencesKey("vibration_stimula")
        val VIBRATION_INTENSITY_KEY = intPreferencesKey("vibration_intensity")

        val SOUND_STIMULA_KEY = booleanPreferencesKey("sound_stimula")
        val SOUND_INTENSITY_KEY = intPreferencesKey("sound_intensity")

        val LIGHT_STIMULA_KEY = booleanPreferencesKey("light_stimula")
        val LIGHT_INTENSITY_KEY = intPreferencesKey("light_intensity")

        //SESSION KEYS
        val START_HOUR_KEY = stringPreferencesKey("start_hour")
        val END_HOUR_KEY = stringPreferencesKey("end_hour")

        val MIN_SESSION = intPreferencesKey("min_session")
        val MAX_SESSION = intPreferencesKey("max_session")

        val IS_ACTIVE = booleanPreferencesKey("is_active")

        val CURRENT_SESSION = stringPreferencesKey("current_session")

        val IS_ALARM_ACTIVE = booleanPreferencesKey("is_active")
    }
    private val Context.dataStore by preferencesDataStore(name = "HEFTOS")

    
    override suspend fun getStartHour() : Flow<String?>{
        return MainApplication.applicationContext().dataStore.data
            .map { preferences ->
                preferences[START_HOUR_KEY] ?: LocalTime.of(7,30).toString()
            }
    }

    override suspend fun getEndHour() : Flow<String?>{
        return MainApplication.applicationContext().dataStore.data
            .map { preferences ->
                preferences[END_HOUR_KEY] ?: LocalTime.of(16,0).toString()
            }
    }

    override suspend fun getMinSession() : Flow<Int?> {
        return MainApplication.applicationContext().dataStore.data
            .map { preferences ->
                preferences[MIN_SESSION] ?: 3
            }
    }

    override suspend fun getMaxSession(): Flow<Int?> {
       return MainApplication.applicationContext().dataStore.data
           .map { preferences ->
               preferences[MAX_SESSION] ?: 5
           }
    }

    override suspend fun getVibration() : Flow<Boolean?> {
       return MainApplication.applicationContext().dataStore.data
           .map { preferences ->
               preferences[VIBRATION_STIMULA_KEY] ?: true
           }
    }

    override suspend fun getVibrationIntensity() : Flow<Int?>{
        return MainApplication.applicationContext().dataStore.data
            .map { preferences ->
                preferences[VIBRATION_INTENSITY_KEY] ?: 1
            }
    }

    override suspend fun getLight() : Flow<Boolean?> {
        return MainApplication.applicationContext().dataStore.data
            .map { preferences ->
                preferences[LIGHT_STIMULA_KEY] ?: false
            }
    }

    override suspend fun getLightIntensity(): Flow<Int?> {
       return MainApplication.applicationContext().dataStore.data
           .map { preferences ->
               preferences[LIGHT_INTENSITY_KEY] ?: 1
           }
    }

    override suspend fun getSound() : Flow<Boolean?> {
       return MainApplication.applicationContext().dataStore.data
           .map { preferences ->
               preferences[SOUND_STIMULA_KEY] ?: false
           }
    }

    override suspend fun getSoundIntensity() : Flow<Int?>{
        return MainApplication.applicationContext().dataStore.data
            .map { preferences ->
                preferences[SOUND_INTENSITY_KEY] ?: 1
            }
    }

    override suspend fun saveStartHour(startHour: String) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[START_HOUR_KEY] = startHour
        }
    }

    override suspend fun saveEndHour(endHour: String) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[END_HOUR_KEY] = endHour
        }
    }

    override suspend fun saveMinSession(min: Int) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[MIN_SESSION] = min
        }
    }

    override suspend fun saveMaxSession(max: Int) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[MAX_SESSION] = max
        }
    }

    override suspend fun saveVibration(status: Boolean) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[VIBRATION_STIMULA_KEY] = status
        }
    }

    override suspend fun saveVibrationIntensity(intensity: Int) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[VIBRATION_INTENSITY_KEY] = intensity
        }
    }

    override suspend fun saveLight(status: Boolean) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[LIGHT_STIMULA_KEY] = status
        }
    }

    override suspend fun saveLightIntensity(intensity: Int) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[LIGHT_INTENSITY_KEY] = intensity
        }
    }

    override suspend fun saveSound(status: Boolean) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[SOUND_STIMULA_KEY] = status
        }
    }

    override suspend fun saveSoundIntensity(intensity: Int) {
        MainApplication.applicationContext().dataStore.edit { preferences ->
            preferences[SOUND_INTENSITY_KEY] = intensity
        }
    }
}