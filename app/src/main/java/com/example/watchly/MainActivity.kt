package com.example.watchly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.watchly.ui.theme.WatchlyTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WatchlyTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        val viewModel = getViewModel<HomeViewModel>()

                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                DiscoverHomeScreen(viewModel, navController)
                            }
                            composable("details/{id}") { backStack ->
                                val id = backStack.arguments?.getString("id")!!.toInt()
                                val vm = getViewModel<DetailsViewModel>()
                                MovieDetailsScreen(id, vm, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

