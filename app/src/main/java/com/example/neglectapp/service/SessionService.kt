package com.example.neglectapp.service

import android.app.*
import android.app.Notification.CATEGORY_ALARM
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat.stopForeground
import androidx.wear.ongoing.OngoingActivity
import androidx.wear.ongoing.Status
import com.example.neglectapp.AlarmActivity
import com.example.neglectapp.R
import com.example.neglectapp.core.Constants.ACTION_SERVICE_CANCEL
import com.example.neglectapp.core.Constants.ACTION_SERVICE_START
import com.example.neglectapp.core.Constants.ACTION_SERVICE_STOP
import com.example.neglectapp.core.Constants.ACTION_SHOW_ALARM
import com.example.neglectapp.core.Constants.ACTION_TRIGGER_ALARM
import com.example.neglectapp.core.Constants.CANCEL_REQUEST_CODE
import com.example.neglectapp.core.Constants.NOTIFICATION_CHANNEL_ID
import com.example.neglectapp.core.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.neglectapp.core.Constants.NOTIFICATION_ID
import com.example.neglectapp.core.Constants.SESSION_STATE
import com.example.neglectapp.data.datastore.LocalDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.ChronoField
import java.util.*
import javax.inject.Inject

@ExperimentalAnimationApi
@AndroidEntryPoint
class SessionService : Service(), KoinComponent {
    @Inject
    lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder
    private val localDataStore: LocalDataStore by inject()

    private val binder = SessionBinder()

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

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.getStringExtra(SESSION_STATE)) {
            SessionState.Started.name -> {
                startForegroundService()
                currentState.value = SessionState.Started
            }
            SessionState.Stopped.name -> {
                currentState.value = SessionState.Stopped
            }
            SessionState.Canceled.name -> {
                currentState.value = SessionState.Stopped
                stopForegroundService()
            }
        }
        intent?.action.let {
            when (it) {
                ACTION_SERVICE_START -> {
                    Log.d("OnStart:", "START DETECTED")
                    startForegroundService()
                    GlobalScope.launch {
                        suspend {
                            gatherSessionData()
                            delay(1000)
                            withContext(Dispatchers.Main) {
                                createSessions()
                            }
                        }.invoke()
                    }
                    currentState.value = SessionState.Started
                    createSessions()
                }
                ACTION_SERVICE_STOP -> {
                    currentState.value = SessionState.Stopped
                }
                ACTION_SERVICE_CANCEL -> {
                    currentState.value = SessionState.Idle
                    stopForegroundService()
                }
                //ACTION FOR DEBUGGING (BUTTON)
                ACTION_TRIGGER_ALARM -> {
                    Log.d("OnStart:", "ALARM TRIGGER DETECTED")
                    triggerAlarm(ACTION_SHOW_ALARM, LocalTime.now().plusSeconds(20))
                }
                ACTION_SHOW_ALARM -> {
                    showAlarm()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService() {
        createNotificationChannel()
        val ongoingActivityStatus = Status.Builder()
            .addTemplate("")
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

    private fun gatherSessionData() {
        localDataStore.getMinSession().onEach {
            Log.d("min", it.toString())
            if (it != null) {
                minSession = it
                minBool = true
            }

        }.launchIn(this.scope)

        localDataStore.getMaxSession().onEach {
            Log.d("max", it.toString())
            if (it != null) {
                maxSession = it
                maxBool = true
            }
        }.launchIn(this.scope)

        localDataStore.getStartHour().onEach {
            Log.d("max", it.toString())
            if (it != null) {
                startHour = it
                startBool = true
            }
        }.launchIn(this.scope)

        localDataStore.getEndHour().onEach {
            Log.d("max", it.toString())
            if (it != null) {
                endHour = it
                endBool = true
            }
        }.launchIn(this.scope)
    }

    private fun createSessions() {
        if (minBool && maxBool && startHour !== "" && endHour !== "") {
            val randomSessionAmount = (minSession..maxSession).random()
            Log.d("TestMin/max", "$minSession/$maxSession")
            Log.d("RANDOM", "$randomSessionAmount")
            for (i in 1..randomSessionAmount) {
                val randomHour: LocalTime = getRandomTimeBetween(startHour, endHour)
                triggerAlarm(ACTION_SHOW_ALARM, randomHour)
            }
            scheduleStopService(ACTION_SERVICE_CANCEL, endHour)
        }
    }

    private fun getRandomTimeBetween(startHour: String, endHour: String): LocalTime {
        val start = LocalTime.parse(startHour)
        val currentTime = LocalTime.now()
        val end = LocalTime.parse(endHour)
        val duration = java.time.Duration.between(currentTime, end)
        val randomMillis = (Random().nextDouble() * duration.toMillis()).toLong()
        return currentTime.plus(java.time.Duration.ofMillis(randomMillis))
    }

    private fun triggerAlarm(action: String, hour: LocalTime) {
        val alarmManager =
            applicationContext.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val triggerIntent = Intent(applicationContext, SessionService::class.java).apply {
            this.action = action
        }
        val pendingIntent = PendingIntent.getService(
            applicationContext,
            hour.hashCode(), triggerIntent, 0
        )

        val datePart = LocalDate.now()

        val dateTime: LocalDateTime = LocalDateTime.of(datePart, hour)
        Log.d("DATE ", "$dateTime")
        Log.d(
            "Alarm scheduled at:",
            "${dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()}"
        )
        val alarmClockInfo = AlarmManager.AlarmClockInfo(
            dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), pendingIntent
        )
        alarmManager?.setAlarmClock(
            alarmClockInfo,
            pendingIntent
        )
    }

    private fun scheduleStopService(action: String, endHour: String) {
        val end: LocalTime = LocalTime.parse(endHour)
        val alarmManager =
            applicationContext.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val triggerIntent = Intent(applicationContext, SessionService::class.java).apply {
            this.action = action
        }
        val pendingIntent = PendingIntent.getService(
            applicationContext,
            CANCEL_REQUEST_CODE, triggerIntent, FLAG_UPDATE_CURRENT
        )
        val datePart = LocalDate.now()

        val dateTime: LocalDateTime = LocalDateTime.of(datePart, end)

        //TRIGGER 1 MINUTE AFTER ENDHOUR, CAUSE ENDHOUR CAN STILL BE USED AS AN ALARM
        val alarmClockInfo = AlarmManager.AlarmClockInfo(
            dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + 60 * 1000,
            pendingIntent
        )
        alarmManager?.setAlarmClock(
            alarmClockInfo,
            pendingIntent
        )
    }

    private fun showAlarm() {
        if (currentState.value == SessionState.Started) {
            applicationContext.startActivity(
                Intent(
                    applicationContext,
                    AlarmActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        } else {
            Log.d("showAlarm", "Applicatie is gestopt/gepauzeerd")
        }
    }

    inner class SessionBinder : Binder() {
        fun getService(): SessionService = this@SessionService
    }
}

enum class SessionState {
    Idle,
    Started,
    Stopped,
    Canceled,
    ClosedOperatingHours
}