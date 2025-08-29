package dev.fizcode.attendance.core.navigation.routes

import kotlinx.serialization.Serializable

sealed interface TopLevelRoute {

    @Serializable
    data object DashboardRoute : TopLevelRoute

    @Serializable
    data object ActivityRoute : TopLevelRoute

    @Serializable
    data object LeaveRoute : TopLevelRoute

    @Serializable
    data object ProfileRoute : TopLevelRoute

}
