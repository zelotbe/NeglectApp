//package com.example.neglectapp.ui.settings
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.update
//
//class SettingsViewModel: ViewModel() {
//    private val _vibrate = MutableStateFlow(true)
//    private val _sound = MutableStateFlow(false)
//    private val _light = MutableStateFlow(false)
//
//    val vibrate: StateFlow<Boolean> = _vibrate
//    val sound: StateFlow<Boolean> = _sound
//    val light: StateFlow<Boolean> = _light
//
//    fun toggleVibrate(){
//        _vibrate.value.let {
//            _vibrate.value = !it
//        }
//    }
//    fun toggleSound(){
//        _sound.value.let {
//            _sound.value = !it
//        }
//    }
//    fun toggleLight(){
//        _light.value.let {
//            _light.value = !it
//        }
//    }
//}