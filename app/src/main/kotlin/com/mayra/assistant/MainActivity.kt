package com.mayra.assistant

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) startMayra()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MayraScreen() }
    }

    private fun startMayra() {
        ContextCompat.startForegroundService(
            this,
            Intent(this, MayraService::class.java).setAction(MayraService.ACTION_START)
        )
    }

    @Composable
    private fun MayraScreen() {
        var active by remember { mutableStateOf(false) }

        MaterialTheme {
            Box(
                modifier = Modifier.fillMaxSize().background(Color(0xFF08090D)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text("MAYRA", style = MaterialTheme.typography.displayMedium, color = Color.White)
                    Text(
                        if (active) "Listening…" else "Voice assistant",
                        color = Color.LightGray
                    )
                    Button(
                        onClick = {
                            if (ContextCompat.checkSelfPermission(
                                    this@MainActivity,
                                    Manifest.permission.RECORD_AUDIO
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                active = !active
                                if (active) startMayra()
                                else stopService(Intent(this@MainActivity, MayraService::class.java))
                            } else {
                                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                            }
                        },
                        shape = CircleShape,
                        modifier = Modifier.size(150.dp)
                    ) {
                        Text(if (active) "STOP" else "START")
                    }
                    Text(
                        "MAYRA uses Gemini Live for real-time voice.",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}