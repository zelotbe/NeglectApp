package com.example.neglectapp.domain.repository

import com.example.neglectapp.domain.model.HeftosSession
import kotlinx.coroutines.flow.Flow

typealias Sessions = List<HeftosSession>
interface SessionRepository {
    fun getSessionsFromRoom(): Flow<Sessions>

    fun getSessionFromRoom(id: Int): HeftosSession

    fun addSessionToRoom(session: HeftosSession)

    fun updateSessionInRoom(session: HeftosSession)

    fun deleteSessionFromRoom(session: HeftosSession)
}