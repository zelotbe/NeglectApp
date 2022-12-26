package com.example.neglectapp.data.room.network

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.neglectapp.data.room.Converters
import com.example.neglectapp.domain.model.HeftosSession

@Database(entities = [HeftosSession::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SessionDb : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
}