package dev.fizcode.attendance.feature.dashboard.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.fizcode.attendance.core.navigation.routes.TopLevelRoute
import dev.fizcode.attendance.feature.dashboard.presentation.DashboardScreen
import kotlinx.serialization.Serializable

@Serializable
data object DashboardBaseRoute

fun NavController.navigateToDashboardScreen(navOptions: NavOptions? = null) =
    navigate(TopLevelRoute.DashboardRoute, navOptions)

fun NavGraphBuilder.dashboardNavGraph(
    topLevelNavPadding: PaddingValues
) = composable<TopLevelRoute.DashboardRoute> {
        DashboardScreen(topLevelNavPadding = topLevelNavPadding)
    }
