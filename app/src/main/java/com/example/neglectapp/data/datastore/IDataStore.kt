package com.example.neglectapp.data.datastore

import kotlinx.coroutines.flow.Flow

interface IDataStore {
    //GETTERS SESSION
    fun getStartHour(): Flow<String?>
    fun getEndHour(): Flow<String?>
    fun getMinSession(): Flow<Int?>
    fun getMaxSession(): Flow<Int?>

    //GETTERS STIMULA
    fun getVibration(): Flow<Boolean?>
    fun getVibrationIntensity(): Flow<Int?>
    fun getLight(): Flow<Boolean?>
    fun getLightIntensity(): Flow<Int?>
    fun getSound(): Flow<Boolean?>
    fun getSoundIntensity(): Flow<Int?>

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