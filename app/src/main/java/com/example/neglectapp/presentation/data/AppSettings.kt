package com.example.neglectapp.presentation.data

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val stimula: PersistentList<StimulaObject> = persistentListOf(StimulaObject(title = "Vibratie", active = true, intensity = 1.0), StimulaObject(title = "Geluid", active = false, intensity = 1.0), StimulaObject(title = "Licht", active = false, intensity = 1.0))
)

@Serializable
data class StimulaObject(
    val title: String,
    var active: Boolean,
    var intensity: Double
)