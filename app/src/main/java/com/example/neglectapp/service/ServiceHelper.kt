package com.example.neglectapp.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.neglectapp.MainActivity
import com.example.neglectapp.core.Constants.CANCEL_REQUEST_CODE
import com.example.neglectapp.core.Constants.CLICK_REQUEST_CODE
import com.example.neglectapp.core.Constants.CREATE_SESSIONS_CODE
import com.example.neglectapp.core.Constants.MAX_SESSION
import com.example.neglectapp.core.Constants.MIN_SESSION
import com.example.neglectapp.core.Constants.RESUME_REQUEST_CODE
import com.example.neglectapp.core.Constants.SESSION_STATE
import com.example.neglectapp.core.Constants.STOP_REQUEST_CODE
import org.checkerframework.checker.units.qual.min

@ExperimentalAnimationApi
object ServiceHelper {

    private const val flag = PendingIntent.FLAG_IMMUTABLE

    fun clickPendingIntent(context: Context): PendingIntent {
        val clickIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(SESSION_STATE, SessionState.Started.name)
        }
        return PendingIntent.getActivity(
            context, CLICK_REQUEST_CODE, clickIntent, flag
        )
    }

    fun stopPendingIntent(context: Context): PendingIntent {
        val stopIntent = Intent(context, SessionService::class.java).apply {
            putExtra(SESSION_STATE, SessionState.Stopped.name)
        }
        return PendingIntent.getService(
            context, STOP_REQUEST_CODE, stopIntent, flag
        )
    }

    fun resumePendingIntent(context: Context): PendingIntent {
        val resumeIntent = Intent(context, SessionService::class.java).apply {
            putExtra(SESSION_STATE, SessionState.Started.name)
        }
        return PendingIntent.getService(
            context, RESUME_REQUEST_CODE, resumeIntent, flag
        )
    }

    fun cancelPendingIntent(context: Context): PendingIntent {
        val cancelIntent = Intent(context, SessionService::class.java).apply {
            putExtra(SESSION_STATE, SessionState.Canceled.name)
        }
        return PendingIntent.getService(
            context, CANCEL_REQUEST_CODE, cancelIntent, flag
        )
    }

    fun triggerForegroundService(context: Context, action: String) {
        Intent(context, SessionService::class.java).apply {
            this.action = action
            context.startService(this)
        }
    }
    fun activateSessionCreation(context: Context, min: Int, max: Int) : PendingIntent{
        val activateIntent = Intent(context, SessionService::class.java).apply{
            var minMax: IntArray = intArrayOf(min, max)
            putExtra("MINMAX", minMax)
        }
        return PendingIntent.getService(
            context, CREATE_SESSIONS_CODE, activateIntent, flag
        )
    }
}
