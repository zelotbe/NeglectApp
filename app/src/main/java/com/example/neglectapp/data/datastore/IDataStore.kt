package com.example.neglectapp.data.datastore

import kotlinx.coroutines.flow.Flow

interface IDataStore {
    //GETTERS SESSION
    suspend fun getStartHour() : Flow<String?>
    suspend fun getEndHour() : Flow<String?>
    suspend fun getMinSession() : Flow<Int?>
    suspend fun getMaxSession() : Flow<Int?>

    //GETTERS STIMULA
    suspend fun getVibration() : Flow<Boolean?>
    suspend fun getVibrationIntensity() : Flow<Int?>
    suspend fun getLight() : Flow<Boolean?>
    suspend fun getLightIntensity() : Flow<Int?>
    suspend fun getSound() : Flow<Boolean?>
    suspend fun getSoundIntensity(): Flow<Int?>

    //SETTERS SESSION
    suspend fun saveStartHour(startHour: String)
    suspend fun saveEndHour(endHour: String)
    suspend fun saveMinSession(min: Int)
    suspend fun saveMaxSession(max: Int)

    //SETTERS STIMULA
    suspend fun saveVibration(status: Boolean)
    suspend fun saveVibrationIntensity(intensity: Int)
    suspend fun saveLight(status: Boolean)
    suspend fun saveLightIntensity(intensity: Int)
    suspend fun saveSound(status: Boolean)
    suspend fun saveSoundIntensity(intensity: Int)

}