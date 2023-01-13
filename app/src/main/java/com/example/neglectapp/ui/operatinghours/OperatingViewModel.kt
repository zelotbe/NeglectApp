package com.example.neglectapp.ui.operatinghours

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OperatingViewModel : ViewModel() {

    private val _showEnd = MutableStateFlow(false)

    val showEnd: StateFlow<Boolean> = _showEnd

    fun toggleEnd() {
        _showEnd.value.let {
            _showEnd.value = !it
        }
    }
}