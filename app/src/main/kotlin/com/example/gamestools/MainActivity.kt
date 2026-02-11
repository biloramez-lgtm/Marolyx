package com.example.gamestools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamestools.ui.screens.HomeScreen
import com.example.gamestools.ui.screens.DiceScreen
import com.example.gamestools.ui.screens.TimerScreen
import com.example.gamestools.ui.screens.SpinnerScreen
import com.example.gamestools.ui.screens.ProbabilityScreen
import com.example.gamestools.ui.theme.GamesToolsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamesToolsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    GamesToolsNavigation(navController)
                }
            }
        }
    }
}

@Composable
fun GamesToolsNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("dice") {
            DiceScreen(navController)
        }
        composable("timer") {
            TimerScreen(navController)
        }
        composable("spinner") {
            SpinnerScreen(navController)
        }
        composable("probability") {
            ProbabilityScreen(navController)
        }
    }
}
