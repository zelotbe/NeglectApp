//package com.example.neglectapp.ui.session
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.example.neglectapp.data.datastore.StoreSessions
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.StateFlow
//import javax.inject.Inject
//
//@HiltViewModel
//class SessionViewModel @Inject constructor(private val ): ViewModel() {
//    private val sessionStore = StoreSessions(context)
//    private val _startHour = sessionStore.getStart
//
//    val startHour: LiveData<String> = _startHour as LiveData<String>
//
////    fun toggleEnd(){
////        _startHour.value.let {
////            _showEnd.value = !it
////        }
////    }
//}