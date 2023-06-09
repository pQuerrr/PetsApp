package com.example.petsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petsapp.utils.Route
import com.example.petsapp.presentation.login.Login
import com.example.petsapp.presentation.registration.Registration
import com.example.petsapp.ui.theme.PetsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.LoginScreen.route,
                ) {
                    composable(route = Route.LoginScreen.route) {
                        Login(
                            onSingUpClick = {
                                navController.navigate(
                                    Route.Registration.route
                                )

                            }
                        )
                    }
                    composable(route = Route.Registration.route){
                        Registration(onSingUpClick = {
                            navController.navigate(
                                Route.LoginScreen.route
                            )
                        },
                            onRegistrationClick = {
                                navController.navigate(
                                    Route.LoginScreen.route
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}




