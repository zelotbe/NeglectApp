package com.example.neglectapp

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.neglectapp.service.SessionService
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    internal lateinit var navController: NavHostController
    private lateinit var sessionService: SessionService
    private var isBound by mutableStateOf(false)
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as SessionService.SessionBinder
            sessionService = binder.getService()
            isBound = true
        }
        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, SessionService::class.java).also{ intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberSwipeDismissableNavController()
            if (isBound) {
                WearApp(
                    modifier = Modifier,
                    swipeDismissableNavController = navController,
                    sessionService = sessionService,
                )
            }
        }
        requestPermissions(Manifest.permission.POST_NOTIFICATIONS)
    }
    private fun requestPermissions(vararg permissions: String) {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            result.entries.forEach {
                Log.d("MainActivity", "${it.key} = ${it.value}")
            }
        }
        requestPermissionLauncher.launch(permissions.asList().toTypedArray())
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isBound = false
    }
}
