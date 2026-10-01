package com.mayra.assistant

import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MayraService : Service() {
    private lateinit var live: GeminiLiveManager

    override fun onCreate() {
        super.onCreate()
        createChannel()
        startForeground(NOTIFICATION_ID, notification())
        live = GeminiLiveManager(applicationContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_START) live.start()
        return START_STICKY
    }

    override fun onDestroy() {
        live.stop()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createChannel() {
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(
            NotificationChannel(
                CHANNEL_ID,
                "MAYRA Voice Assistant",
                NotificationManager.IMPORTANCE_LOW
            )
        )
    }

    private fun notification(): Notification =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("MAYRA")
            .setContentText("Voice assistant is active")
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setOngoing(true)
            .build()

    companion object {
        const val ACTION_START = "MAYRA_START"
        private const val CHANNEL_ID = "mayra_voice"
        private const val NOTIFICATION_ID = 1001
    }
}