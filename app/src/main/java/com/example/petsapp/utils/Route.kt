package com.example.petsapp.utils

import com.example.petsapp.utils.Const.LOGIN_SCREEN
import com.example.petsapp.utils.Const.MAIN_SCREEN
import com.example.petsapp.utils.Const.REGISTRATION_SCREEN

sealed class Route(val route: String) {
    object LoginScreen : Route(LOGIN_SCREEN)
    object Registration : Route(REGISTRATION_SCREEN)
    object MainScreen : Route(MAIN_SCREEN)
}


object Const {
    const val LOGIN_SCREEN = "login"
    const val REGISTRATION_SCREEN = "registration"
    const val MAIN_SCREEN = "mainscreen"
}