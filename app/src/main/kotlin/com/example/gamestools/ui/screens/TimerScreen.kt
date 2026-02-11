package com.example.gamestools.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun TimerScreen(navController: NavHostController) {
    var timeInSeconds by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var inputMinutes by remember { mutableStateOf("") }
    var inputSeconds by remember { mutableStateOf("") }

    LaunchedEffect(isRunning) {
        while (isRunning && timeInSeconds > 0) {
            delay(1000)
            timeInSeconds--
            if (timeInSeconds == 0) {
                isRunning = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header with back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Timer - المؤقت",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Time Display
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = String.format("%02d:%02d", timeInSeconds / 60, timeInSeconds % 60),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // Input Fields
        if (!isRunning && timeInSeconds == 0) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = inputMinutes,
                    onValueChange = { inputMinutes = it },
                    label = { Text("دقائق") },
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = inputSeconds,
                    onValueChange = { inputSeconds = it },
                    label = { Text("ثواني") },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Control Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!isRunning && timeInSeconds == 0) {
                Button(
                    onClick = {
                        val minutes = inputMinutes.toIntOrNull() ?: 0
                        val seconds = inputSeconds.toIntOrNull() ?: 0
                        timeInSeconds = minutes * 60 + seconds
                        inputMinutes = ""
                        inputSeconds = ""
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("تعيين", color = MaterialTheme.colorScheme.onPrimary)
                }
            } else {
                if (isRunning) {
                    Button(
                        onClick = { isRunning = false },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("إيقاف", color = MaterialTheme.colorScheme.onSecondary)
                    }
                } else if (timeInSeconds > 0) {
                    Button(
                        onClick = { isRunning = true },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("تشغيل", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
                
                Button(
                    onClick = {
                        isRunning = false
                        timeInSeconds = 0
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("إعادة تعيين", color = MaterialTheme.colorScheme.onError)
                }
            }
        }
    }
}
