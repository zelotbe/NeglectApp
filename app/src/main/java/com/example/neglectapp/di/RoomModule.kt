package com.example.neglectapp.di

import android.content.Context
import androidx.room.Room
import com.example.neglectapp.core.Constants.SESSION_TABLE
import com.example.neglectapp.data.room.network.SessionDao
import com.example.neglectapp.data.room.network.SessionDb
import com.example.neglectapp.data.room.repository.SessionRepositoryImpl
import com.example.neglectapp.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    fun provideSessionDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        SessionDb::class.java,
        SESSION_TABLE
    ).build()

    @Provides
    fun provideSessionDao(
        sessionDb: SessionDb
    ) = sessionDb.sessionDao()

    @Provides
    fun provideSessionRepository(
        sessionDao: SessionDao
    ): SessionRepository = SessionRepositoryImpl(
        sessionDao = sessionDao
    )
}