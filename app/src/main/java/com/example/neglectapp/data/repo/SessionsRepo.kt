package com.example.neglectapp.data.repo

import android.content.Context
import com.example.neglectapp.data.datastore.StoreSessions
import kotlinx.coroutines.flow.Flow

class SessionsRepo private constructor(
    private val sessionsStore: StoreSessions
) {
    val sessionIsActive : Flow<Boolean> = sessionsStore.getActive
    val currentSession : Flow<String> = sessionsStore.getCurrentSession

    suspend fun setActiveSession(status: Boolean){
        sessionsStore.saveActive(status)
    }

    suspend fun setCurrentSession(session: String){
        sessionsStore.saveCurrentSession(session)
    }
    companion object {
        @Volatile private var INSTANCE: SessionsRepo? = null

        fun getInstance(context: Context): SessionsRepo {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SessionsRepo(
                    StoreSessions(context))
                    .also { INSTANCE = it }
            }
        }
    }
}