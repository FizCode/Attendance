package dev.fizcode.attendance.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.fizcode.attendance.core.navigation.routes.RootRoute
import dev.fizcode.attendance.feature.login.presentation.LoginScreen
import kotlinx.serialization.Serializable

@Serializable data object LoginRoute

fun NavGraphBuilder.loginNavGraph() {
    composable<RootRoute.LoginRoute> {
        LoginScreen()
    }
}
