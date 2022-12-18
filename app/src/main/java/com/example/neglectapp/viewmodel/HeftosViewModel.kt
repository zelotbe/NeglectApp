package com.example.neglectapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neglectapp.data.datastore.LocalDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalTime


class HeftosViewModel : ViewModel(), KoinComponent{
    private val tag = HeftosViewModel::class.java.simpleName
    private val localDataStore: LocalDataStore by inject()
    private val _startHour = MutableStateFlow("")
    var startHour: StateFlow<String> = _startHour

    private val _endHour = MutableStateFlow("")
    var endHour: StateFlow<String> = _endHour

    private val _minSession = MutableStateFlow(0)
    var minSession: StateFlow<Int> = _minSession

    private val _maxSession = MutableStateFlow(0)
    var maxSession: StateFlow<Int> = _maxSession

    private val _vibration = MutableStateFlow(false)
    var vibration: StateFlow<Boolean> = _vibration

    private val _light = MutableStateFlow(false)
    var light: StateFlow<Boolean> = _light

    private val _sound = MutableStateFlow(false)
    var sound: StateFlow<Boolean> = _sound
    
    private val _vibrationIntensity = MutableStateFlow(0)
    var vibrationIntensity: StateFlow<Int> = _vibrationIntensity

    private val _lightIntensity = MutableStateFlow(0)
    var lightIntensity: StateFlow<Int> = _lightIntensity

    private val _soundIntensity = MutableStateFlow(0)
    var soundIntensity: StateFlow<Int> = _soundIntensity

    init {
        getStartHour()
        getEndHour()
        getMinSession()
        getMaxSession()
        getVibration()
        getSound()
        getLight()
        getVibrationIntensity()
        getSoundIntensity()
        getLightIntensity()
    }

    private fun getStartHour(){
        viewModelScope.launch {
            localDataStore.getStartHour().collect{
                if (it != null) {
                  _startHour.value = it
                }
            }
        }
    }
    private fun getEndHour(){
        viewModelScope.launch() {
            localDataStore.getEndHour().collect{
                if (it != null) {
                    _endHour.value = it
                }
            }
        }
    }
    private fun getMinSession(){
        viewModelScope.launch() {
            localDataStore.getMinSession().collect{
                if (it != null) {
                    _minSession.value = it
                }
            }
        }
    }
    private fun getMaxSession(){
        viewModelScope.launch() {
            localDataStore.getMaxSession().collect{
                if (it != null) {
                    _maxSession.value = it
                }
            }
        }
    }
    private fun getVibration(){
        viewModelScope.launch() {
            localDataStore.getVibration().collect{
                if (it != null) {
                    _vibration.value = it
                }
            }
        }
    }
    private fun getLight(){
        viewModelScope.launch() {
            localDataStore.getLight().collect{
                if (it != null) {
                    _light.value = it
                }
            }
        }
    }
    private fun getSound(){
        viewModelScope.launch() {
            localDataStore.getSound().collect{
                if (it != null) {
                   _sound.value = it
                }
            }
        }
    }
    private fun getVibrationIntensity(){
        viewModelScope.launch() {
            localDataStore.getVibrationIntensity().collect{
                if (it != null) {
                    _vibrationIntensity.value = it
                }
            }
        }
    }
    private fun getLightIntensity(){
        viewModelScope.launch() {
            localDataStore.getLightIntensity().collect{
                if (it != null) {
                   _lightIntensity.value = it
                }
            }
        }
    }

    private fun getSoundIntensity(){
        viewModelScope.launch() {
            localDataStore.getSoundIntensity().collect{
                if (it != null) {
                    _soundIntensity.value = it
                }
            }
        }
    }

    //SAVE - SETTERS
    fun saveStartHour(time: String){
        viewModelScope.launch(){
            localDataStore.saveStartHour(time)
        }
    }
    fun saveEndHour(time: String){
        viewModelScope.launch(){
            localDataStore.saveEndHour(time)
        }
    }
    fun saveMinSession(int: Int){
        viewModelScope.launch(){
            localDataStore.saveMinSession(int)
        }
    }
    fun saveMaxSession(int: Int){
        viewModelScope.launch(){
            localDataStore.saveMaxSession(int)
        }
    }
    fun saveVibration(bool: Boolean){
        viewModelScope.launch(){
            localDataStore.saveVibration(bool)
        }
    }
    fun saveSound(bool: Boolean){
        viewModelScope.launch(){
            localDataStore.saveSound(bool)
        }
    }
    fun saveLight(bool: Boolean){
        viewModelScope.launch(){
            localDataStore.saveLight(bool)
        }
    }
    fun saveVibrationIntensity(intensity: Int){
        viewModelScope.launch(){
            localDataStore.saveVibrationIntensity(intensity)
        }
    }

    fun saveSoundIntensity(intensity: Int){
        viewModelScope.launch(){
            localDataStore.saveSoundIntensity(intensity)
        }
    }

    fun saveLightIntensity(intensity: Int){
        viewModelScope.launch(){
            localDataStore.saveLightIntensity(intensity)
        }
    }
}