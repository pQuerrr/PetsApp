package com.example.petsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petsapp.presentation.events.AddEventsScreen
import com.example.petsapp.presentation.events.EventsScreen
import com.example.petsapp.presentation.forum.ForumScreen
import com.example.petsapp.presentation.forumMessages.ForumMessagesScreen
import com.example.petsapp.utils.Route
import com.example.petsapp.presentation.login.Login
import com.example.petsapp.presentation.registration.Registration
import com.example.petsapp.presentation.map.MapScreen
import com.example.petsapp.presentation.profile.ProfileScreen
import com.example.petsapp.ui.theme.PetsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsAppTheme {
                val navController = rememberNavController()
                val toolbarDestinations = listOf(
                    { navController.navigate(Route.MapScreen.route) },
                    { navController.navigate(Route.ForumScreen.route) },
                    { navController.navigate(Route.EventsScreen.route) },
                    { navController.navigate(Route.ProfileScreen.route) }
                )
                NavHost(
                    navController = navController,

                    startDestination = Route.EventsScreen.route,
                ) {
                    composable(route = Route.LoginScreen.route) {
                        Login(
                            onSingUpClick = {
                                navController.navigate(
                                    Route.Registration.route
                                )
                            },
                            nextScreen = {
                                navController.navigate(
                                    Route.MapScreen.route
                                )
                            }
                        )
                    }
                    composable(route = Route.Registration.route) {
                        Registration(onSingUpClick = {
                            navController.navigate(
                                Route.LoginScreen.route
                            )
                        },
                            nextScreen = {
                                navController.navigate(
                                    Route.MapScreen.route
                                )
                            }
                        )
                    }
                    composable(route = Route.MapScreen.route) {
                        MapScreen(
                            toolbarDestinations = toolbarDestinations
                        )
                    }
                    composable(route = Route.ForumScreen.route) {
                        ForumScreen(
                            toolbarDestinations = toolbarDestinations,
                            onReadThemeClicked = {navController.navigate(Route.ThemeScreen.route)}
                        )
                    }
                    composable(route = Route.EventsScreen.route) {
                        EventsScreen(
                            toolbarDestinations = toolbarDestinations
                        )
                    }
                    composable(route = Route.ProfileScreen.route) {
                        ProfileScreen(
                            toolbarDestinations = toolbarDestinations
                        )
                    }
                    composable(route = Route.ThemeScreen.route){
                        ForumMessagesScreen { navController.popBackStack() }
                    }
                }
            }
        }
    }
}


