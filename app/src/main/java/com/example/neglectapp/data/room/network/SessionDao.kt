package com.example.neglectapp.data.room.network

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.neglectapp.core.Constants.SESSION_TABLE
import com.example.neglectapp.domain.model.HeftosSession
import com.example.neglectapp.domain.repository.Sessions
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Query("SELECT * FROM $SESSION_TABLE")
    fun getSessions(): Flow<Sessions>

    @Query("SELECT * FROM $SESSION_TABLE WHERE id = :id")
    fun getSession(id: Int): HeftosSession

    @Insert(onConflict = IGNORE)
    fun addSession(session: HeftosSession)

    @Update
    fun updateSession(session: HeftosSession)

    @Delete
    fun deleteSession(session: HeftosSession)

    @Query("SELECT COUNT(id) FROM $SESSION_TABLE WHERE has_interacted = 1 ")
    fun getAmountInteracted(): Flow<Int>

    @Query("SELECT COUNT(id) FROM $SESSION_TABLE WHERE has_interacted = 0 ")
    fun getAmountNotInteracted(): Flow<Int>
}