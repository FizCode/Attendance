package dev.fizcode.attendance.core.navigation.routes

import kotlinx.serialization.Serializable

sealed interface RootRoute {

    @Serializable
    data object LoginRoute : RootRoute

    @Serializable
    data object TopLevelRoute : RootRoute

}
