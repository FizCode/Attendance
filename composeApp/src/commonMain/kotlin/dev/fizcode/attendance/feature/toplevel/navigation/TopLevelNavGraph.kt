package dev.fizcode.attendance.feature.toplevel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.fizcode.attendance.core.navigation.routes.RootRoute
import dev.fizcode.attendance.feature.toplevel.presentation.TopLevelScreen
import dev.fizcode.attendance_api.util.ContextFactory

fun NavController.navigateToTopLevelScreen(navOptions: NavOptions? = null) =
    navigate(RootRoute.TopLevelRoute, navOptions)

fun NavGraphBuilder.topLevelNavGraph(
    platformContext: ContextFactory
) {
    composable<RootRoute.TopLevelRoute> {
        TopLevelScreen(
            platformContext = platformContext,
            navHostController = rememberNavController()
        )
    }
}