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
//class StoreLight(private val context: Context) {
//
//    companion object{
//        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("StimulaLight")
//        val LIGHT_STIMULA_KEY = booleanPreferencesKey("light_stimula")
//        val LIGHT_INTENSITY_KEY = doublePreferencesKey("light_intensity")
//    }
//
//    val getLight: Flow<Boolean?> = context.dataStore.data
//        .map { preferences ->
//            preferences[LIGHT_STIMULA_KEY]
//        }
//    val getLightIntensity: Flow<Double?> = context.dataStore.data
//        .map { preferences ->
//            preferences[LIGHT_INTENSITY_KEY] ?: 1.0
//        }
//
//    suspend fun saveLight(status: Boolean){
//        context.dataStore.edit { preferences ->
//            preferences[LIGHT_STIMULA_KEY] = status
//        }
//    }
//    suspend fun saveLightIntensity(intensity: Double){
//        context.dataStore.edit { preferences ->
//            preferences[LIGHT_INTENSITY_KEY] = intensity
//        }
//    }
//}