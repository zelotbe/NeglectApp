package com.example.neglectapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreStimula(private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("StimulaStore")
        val VIBRATION_STIMULA_KEY = booleanPreferencesKey("vibration_stimula")
        val VIBRATION_INTENSITY_KEY = intPreferencesKey("vibration_intensity")

        val SOUND_STIMULA_KEY = booleanPreferencesKey("sound_stimula")
        val SOUND_INTENSITY_KEY = intPreferencesKey("sound_intensity")

        val LIGHT_STIMULA_KEY = booleanPreferencesKey("light_stimula")
        val LIGHT_INTENSITY_KEY = intPreferencesKey("light_intensity")
    }

    val getVibration: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[VIBRATION_STIMULA_KEY] ?: true
        }
    val getVibrationIntensity: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[VIBRATION_INTENSITY_KEY] ?: 1
        }

    suspend fun saveVibration(status: Boolean){
        context.dataStore.edit { preferences ->
            preferences[VIBRATION_STIMULA_KEY] = status
        }
    }
    suspend fun saveVibrationIntensity(intensity: Int){
        context.dataStore.edit { preferences ->
            preferences[VIBRATION_INTENSITY_KEY] = intensity
        }
    }

    val getSound: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[SOUND_STIMULA_KEY] ?: false
        }
    val getSoundIntensity: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[SOUND_INTENSITY_KEY] ?: 1
        }

    suspend fun saveSound(status: Boolean){
        context.dataStore.edit { preferences ->
            preferences[SOUND_STIMULA_KEY] = status
        }
    }
    suspend fun saveSoundIntensity(intensity: Int){
        context.dataStore.edit { preferences ->
            preferences[SOUND_INTENSITY_KEY] = intensity
        }
    }

    val getLight: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[LIGHT_STIMULA_KEY] ?: false
        }
    val getLightIntensity: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[LIGHT_INTENSITY_KEY] ?: 1
        }

    suspend fun saveLight(status: Boolean){
        context.dataStore.edit { preferences ->
            preferences[LIGHT_STIMULA_KEY] = status
        }
    }
    suspend fun saveLightIntensity(intensity: Int){
        context.dataStore.edit { preferences ->
            preferences[LIGHT_INTENSITY_KEY] = intensity
        }
    }
}