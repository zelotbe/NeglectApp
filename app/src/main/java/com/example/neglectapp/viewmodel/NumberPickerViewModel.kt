package com.example.neglectapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NumberPickerViewModel: ViewModel() {
    private val _showMax = MutableStateFlow(false)

    val showMax: StateFlow<Boolean> = _showMax

    fun toggleMax() {
        _showMax.value.let {
            _showMax.value = !it
        }
    }
}