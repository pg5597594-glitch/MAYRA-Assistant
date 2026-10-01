package com.mayra.assistant

import android.content.Context
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.LiveConnectConfig
import kotlinx.coroutines.*

/*
 * IMPORTANT:
 * Do not commit a real Gemini API key to GitHub.
 * Configure authentication using a secure backend/ephemeral token before
 * shipping a production APK.
 *
 * This class is the integration point for the Gemini Live SDK.
 */
class GeminiLiveManager(private val context: Context) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var client: Client? = null

    fun start() {
        scope.launch {
            try {
                /*
                 * Model:
                 * gemini-3.1-flash-live-preview
                 *
                 * The exact authentication method should be selected for the
                 * deployment (prefer ephemeral auth from a backend).
                 *
                 * Audio capture/playback and barge-in are intentionally kept
                 * in AudioEngine so they can be tested independently.
                 */
                client = Client()
                // TODO: connect Live session with secure authentication.
            } catch (_: Throwable) {
                // TODO: expose reconnect/error state to UI.
            }
        }
    }

    fun stop() {
        scope.cancel()
        client?.close()
        client = null
    }
}