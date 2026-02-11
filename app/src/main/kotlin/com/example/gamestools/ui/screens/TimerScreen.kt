package com.example.gamestools.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(navController: NavHostController) {

    var timeInSeconds by rememberSaveable { mutableStateOf(0) }
    var isRunning by rememberSaveable { mutableStateOf(false) }
    var inputMinutes by rememberSaveable { mutableStateOf("") }
    var inputSeconds by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(isRunning) {
        while (isRunning && timeInSeconds > 0) {
            delay(1000)
            timeInSeconds--
            if (timeInSeconds == 0) {
                isRunning = false
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Timer - المؤقت") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Time Display
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = String.format(
                            "%02d:%02d",
                            timeInSeconds / 60,
                            timeInSeconds % 60
                        ),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Input Fields (only when timer is stopped and zero)
            if (!isRunning && timeInSeconds == 0) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedTextField(
                        value = inputMinutes,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() }) {
                                inputMinutes = it
                            }
                        },
                        label = { Text("دقائق") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = inputSeconds,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() }) {
                                inputSeconds = it
                            }
                        },
                        label = { Text("ثواني") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                Button(
                    onClick = {
                        val minutes = inputMinutes.toIntOrNull() ?: 0
                        val secondsRaw = inputSeconds.toIntOrNull() ?: 0
                        val seconds = secondsRaw.coerceIn(0, 59)

                        timeInSeconds = minutes * 60 + seconds
                        inputMinutes = ""
                        inputSeconds = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("تعيين")
                }
            }

            // Control Buttons
            if (timeInSeconds > 0) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    if (isRunning) {
                        Button(
                            onClick = { isRunning = false },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text("إيقاف")
                        }
                    } else {
                        Button(
                            onClick = { isRunning = true },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("تشغيل")
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
                        Text("إعادة تعيين")
                    }
                }
            }
        }
    }
}
