package com.example.neglectapp.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.neglectapp.MainActivity
import com.example.neglectapp.core.Constants.CANCEL_REQUEST_CODE
import com.example.neglectapp.core.Constants.CLICK_REQUEST_CODE
import com.example.neglectapp.core.Constants.RESUME_REQUEST_CODE
import com.example.neglectapp.core.Constants.SESSION_STATE
import com.example.neglectapp.core.Constants.STOP_REQUEST_CODE

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
}
