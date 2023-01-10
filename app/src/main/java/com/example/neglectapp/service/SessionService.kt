package com.example.neglectapp.service

import android.annotation.SuppressLint
import android.app.*
import android.app.Notification.CATEGORY_ALARM
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.wear.ongoing.OngoingActivity
import androidx.wear.ongoing.Status
import com.example.neglectapp.AlarmActivity
import com.example.neglectapp.R
import com.example.neglectapp.core.Constants.ACTION_SERVICE_CANCEL
import com.example.neglectapp.core.Constants.ACTION_SERVICE_START
import com.example.neglectapp.core.Constants.ACTION_SERVICE_STOP
import com.example.neglectapp.core.Constants.ACTION_SHOW_ALARM
import com.example.neglectapp.core.Constants.ACTION_TRIGGER_ALARM
import com.example.neglectapp.core.Constants.NOTIFICATION_CHANNEL_ID
import com.example.neglectapp.core.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.neglectapp.core.Constants.NOTIFICATION_ID
import com.example.neglectapp.core.Constants.SESSION_STATE
import com.example.neglectapp.core.formatTime
import com.example.neglectapp.core.pad
import com.example.neglectapp.data.datastore.LocalDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*
import javax.inject.Inject
import kotlin.time.Duration


@ExperimentalAnimationApi
@AndroidEntryPoint
class SessionService() : Service(), KoinComponent {
    @Inject
    lateinit var notificationManager: NotificationManager
    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder
    private val localDataStore: LocalDataStore by inject()

    private val binder = SessionBinder()

    private var duration: Duration = Duration.ZERO
    private lateinit var timer: Timer

    var seconds = mutableStateOf("00")
        private set
    var minutes = mutableStateOf("00")
        private set
    var hours = mutableStateOf("00")
        private set

    var currentState = mutableStateOf(SessionState.Idle)
        private set
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    private var minSession = 0
    private var maxSession = 0
    private var startHour = ""
    private var endHour = ""
    private var minBool = false
    private var maxBool = false
    private var startBool = false
    private var endBool = false

    override fun onBind(p0: Intent?): IBinder {return binder}

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.getStringExtra(SESSION_STATE)) {
            SessionState.Started.name -> {
                setStopButton()
                startForegroundService()

                currentState.value = SessionState.Started
            }
            SessionState.Stopped.name -> {
                stopStopwatch()
                setResumeButton()
            }
            SessionState.Canceled.name -> {
                stopStopwatch()
                cancelStopwatch()
                stopForegroundService()
            }
        }
        intent?.action.let {
            when (it) {
                ACTION_SERVICE_START -> {
                    Log.d("OnStart:", "START DETECTED")
                    setStopButton()
                    startForegroundService()
                    scope.launch { gatherSessionData() }
                    currentState.value = SessionState.Started
                }
                ACTION_SERVICE_STOP -> {
                    stopStopwatch()
                    setResumeButton()
                }
                ACTION_SERVICE_CANCEL -> {
                    stopStopwatch()
                    cancelStopwatch()
                    stopForegroundService()
                }
                ACTION_TRIGGER_ALARM -> {
                    Log.d("OnStart:", "ALARM TRIGGER DETECTED")
                    triggerAlarm(ACTION_SHOW_ALARM)
                }
                ACTION_SHOW_ALARM -> {
                   showAlarm()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun stopStopwatch() {
        if (this::timer.isInitialized) {
            timer.cancel()
        }
        currentState.value = SessionState.Stopped
    }

    private fun cancelStopwatch() {
        duration = Duration.ZERO
        currentState.value = SessionState.Idle
        updateTimeUnits()
    }

    private fun updateTimeUnits() {
        duration.toComponents { hours, minutes, seconds, _ ->
            this@SessionService.hours.value = hours.toInt().pad()
            this@SessionService.minutes.value = minutes.pad()
            this@SessionService.seconds.value = seconds.pad()
        }
    }

    private fun startForegroundService() {
        createNotificationChannel()
        val ongoingActivityStatus = Status.Builder()
            .addTemplate("HIER KOMT RESTERENDE TIJD")
            .build()

        val ongoingActivity =
            OngoingActivity.Builder(applicationContext, NOTIFICATION_ID, notificationBuilder)
//                .setAnimatedIcon(R.drawable.animated_walk)
                .setStaticIcon(R.drawable.ic_baseline_flaky_24)
                .setTouchIntent(ServiceHelper.clickPendingIntent(applicationContext))
                .setCategory(CATEGORY_ALARM)
                .setStatus(ongoingActivityStatus)
                .build()

        ongoingActivity.apply(applicationContext)

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun stopForegroundService() {
        notificationManager.cancel(NOTIFICATION_ID)
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

    private fun updateNotification(hours: String, minutes: String, seconds: String) {
        notificationManager.notify(
            NOTIFICATION_ID,
            notificationBuilder.setContentText(
//                "Hello"
                formatTime(
                    hours = hours,
                    minutes = minutes,
                    seconds = seconds,
                )
            ).build()
        )
    }

    @SuppressLint("RestrictedApi")
    private fun setStopButton() {
        notificationBuilder.mActions.removeAt(0)
        notificationBuilder.mActions.add(
            0,
            NotificationCompat.Action(
                0,
                "Stop",
                ServiceHelper.stopPendingIntent(this)
            )
        )
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    @SuppressLint("RestrictedApi")
    private fun setResumeButton() {
        notificationBuilder.mActions.removeAt(0)
        notificationBuilder.mActions.add(
            0,
            NotificationCompat.Action(
                0,
                "Resume",
                ServiceHelper.resumePendingIntent(this)
            )
        )
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private suspend fun gatherSessionData(){
        localDataStore.getMinSession().onEach {
            Log.d("min", it.toString())
            if (it != null) {
                minSession = it
                minBool = true
                createSessions()
            }

        }.launchIn(this.scope)

        localDataStore.getMaxSession().onEach {
            Log.d("max", it.toString())
            if (it != null) {
                maxSession = it
                maxBool = true
                createSessions()
            }
        }.launchIn(this.scope)

        localDataStore.getStartHour().onEach {
            Log.d("max", it.toString())
            if (it != null) {
                startHour = it
                startBool = true
                createSessions()
            }
        }.launchIn(this.scope)

        localDataStore.getEndHour().onEach {
            Log.d("max", it.toString())
            if (it != null) {
                endHour = it
                endBool = true
                createSessions()
            }
        }.launchIn(this.scope)

    }

    private fun createSessions(){
        if (minBool && maxBool && startHour !== "" && endHour !== "") {
            Log.d("TestMin/max", "$minSession/$maxSession")
            triggerAlarm(ACTION_TRIGGER_ALARM)
        }
    }

    private fun triggerAlarm(action: String) {
        Log.d("SessionService:", "Triggered Alarm")
        val alarmManager =
            applicationContext.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val triggerIntent = Intent(applicationContext, SessionService::class.java).apply{
            this.action = action
        }
        val pendingIntent = PendingIntent.getService(applicationContext,
            0, triggerIntent,0
        )

        alarmManager?.setAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 60 * 100,
           pendingIntent
        )
    }
    private fun showAlarm(){
        applicationContext.startActivity(Intent(applicationContext, AlarmActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
    inner class SessionBinder : Binder() {
        fun getService(): SessionService = this@SessionService
    }
}
enum class SessionState {
    Idle,
    Started,
    Stopped,
    Canceled
}