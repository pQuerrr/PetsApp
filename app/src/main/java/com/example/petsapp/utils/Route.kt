package com.example.petsapp.utils

import com.example.petsapp.utils.Const.ADD_EVENTS_SCREEN
import com.example.petsapp.utils.Const.ADD_FORUM_SCREEN
import com.example.petsapp.utils.Const.EVENTS_SCREEN
import com.example.petsapp.utils.Const.FORUM_SCREEN
import com.example.petsapp.utils.Const.LOGIN_SCREEN
import com.example.petsapp.utils.Const.MAP_SCREEN
import com.example.petsapp.utils.Const.PROFILE_SCREEN
import com.example.petsapp.utils.Const.REGISTRATION_SCREEN

sealed class Route(val route: String) {

    object LoginScreen : Route(LOGIN_SCREEN)
    object Registration : Route(REGISTRATION_SCREEN)
    object MapScreen : Route(MAP_SCREEN)
    object ForumScreen : Route(FORUM_SCREEN)
    object AddForumScreen: Route(ADD_FORUM_SCREEN)
    object EventsScreen : Route(EVENTS_SCREEN)
    object AddEventsScreen : Route(ADD_EVENTS_SCREEN)
    object ProfileScreen : Route(PROFILE_SCREEN)


}


object Const {
    const val LOGIN_SCREEN = "login"
    const val REGISTRATION_SCREEN = "registration"
    const val MAP_SCREEN = "mapscreen"
    const val FORUM_SCREEN = "forumscreen"
    const val EVENTS_SCREEN = "eventsscreen"
    const val ADD_EVENTS_SCREEN = "addeventsscreen"
    const val PROFILE_SCREEN = "profilescreen"
    const val ADD_FORUM_SCREEN = "addforumscreen"
}