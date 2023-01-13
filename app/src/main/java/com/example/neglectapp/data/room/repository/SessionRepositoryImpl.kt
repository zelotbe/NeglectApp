package com.example.neglectapp.data.room.repository

import com.example.neglectapp.data.room.network.SessionDao
import com.example.neglectapp.domain.model.HeftosSession
import com.example.neglectapp.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow

class SessionRepositoryImpl(
    private val sessionDao: SessionDao
) : SessionRepository {
    override fun getSessionsFromRoom() = sessionDao.getSessions()

    override fun getSessionFromRoom(id: Int) = sessionDao.getSession(id)

    override fun addSessionToRoom(session: HeftosSession) = sessionDao.addSession(session)

    override fun updateSessionInRoom(session: HeftosSession) = sessionDao.updateSession(session)

    override fun deleteSessionFromRoom(session: HeftosSession) = sessionDao.deleteSession(session)

    override fun getAmountInteractedFromRoom(): Flow<Int> = sessionDao.getAmountInteracted()
    override fun getAmountNotInteractedFromRoom(): Flow<Int> = sessionDao.getAmountNotInteracted()
}