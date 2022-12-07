//package com.example.neglectapp.data.datastore
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.booleanPreferencesKey
//import androidx.datastore.preferences.core.doublePreferencesKey
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class StoreSound(private val context: Context) {
//
//    companion object{
//        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("StimulaSound")
//        val SOUND_STIMULA_KEY = booleanPreferencesKey("sound_stimula")
//        val SOUND_INTENSITY_KEY = doublePreferencesKey("sound_intensity")
//    }
//
//    val getSound: Flow<Boolean?> = context.dataStore.data
//        .map { preferences ->
//            preferences[SOUND_STIMULA_KEY]
//        }
//    val getSoundIntensity: Flow<Double?> = context.dataStore.data
//        .map { preferences ->
//            preferences[SOUND_INTENSITY_KEY] ?: 1.0
//        }
//
//    suspend fun saveSound(status: Boolean){
//        context.dataStore.edit { preferences ->
//            preferences[SOUND_STIMULA_KEY] = status
//        }
//    }
//    suspend fun saveSoundIntensity(intensity: Double){
//        context.dataStore.edit { preferences ->
//            preferences[SOUND_INTENSITY_KEY] = intensity
//        }
//    }
//}