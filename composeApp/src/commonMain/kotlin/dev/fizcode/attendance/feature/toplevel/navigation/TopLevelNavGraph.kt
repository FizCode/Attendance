package dev.fizcode.attendance.feature.toplevel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.fizcode.attendance.core.navigation.routes.RootRoute
import dev.fizcode.attendance.feature.toplevel.presentation.TopLevelScreen

fun NavController.navigateToTopLevelScreen(navOptions: NavOptions? = null) =
    navigate(RootRoute.TopLevelRoute, navOptions)

fun NavGraphBuilder.topLevelNavGraph() {
    composable<RootRoute.TopLevelRoute> {
        TopLevelScreen(navHostController = rememberNavController())
    }
}