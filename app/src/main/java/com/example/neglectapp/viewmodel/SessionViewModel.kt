package com.example.neglectapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neglectapp.domain.model.HeftosSession
import com.example.neglectapp.domain.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val repo: SessionRepository
) : ViewModel() {
    var session by mutableStateOf(HeftosSession(0, LocalDateTime.now(), false, 0))
        private set
    var openDialog by mutableStateOf(false)

    val sessions = repo.getSessionsFromRoom()
    var amountInteracted = repo.getAmountInteractedFromRoom()
    var amountNotInteracted = repo.getAmountNotInteractedFromRoom()

//    fun getBook(id: Int) = viewModelScope.launch(Dispatchers.IO) {
//        book = repo.getBookFromRoom(id)
//    }
//
    fun addSession(session: HeftosSession) = viewModelScope.launch(Dispatchers.IO) {
        repo.addSessionToRoom(session)
    }
}