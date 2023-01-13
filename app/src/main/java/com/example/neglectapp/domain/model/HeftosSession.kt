package com.example.neglectapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.neglectapp.core.Constants.SESSION_TABLE
import java.time.LocalDateTime

@Entity(tableName = SESSION_TABLE)
data class HeftosSession(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "current_datetime") val currentDateTime: LocalDateTime?,
    @ColumnInfo(name = "has_interacted") val hasInteracted: Boolean?,
    @ColumnInfo(name = "heart_rate") val heartRate: Int?
)