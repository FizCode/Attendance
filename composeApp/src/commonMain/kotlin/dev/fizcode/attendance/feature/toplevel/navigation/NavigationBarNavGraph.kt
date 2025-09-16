package dev.fizcode.attendance.feature.toplevel.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.fizcode.attendance.core.navigation.routes.TopLevelRoute
import dev.fizcode.attendance.feature.dashboard.navigation.dashboardNavGraph
import dev.fizcode.attendance_api.util.ContextFactory

@Composable
internal fun NavigationBarNavGraph(
    platformContext: ContextFactory,
    navHostController: NavHostController,
    topLevelNavPadding: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = TopLevelRoute.DashboardRoute
    ) {
        dashboardNavGraph(
            platformContext = platformContext,
            topLevelNavPadding = topLevelNavPadding
        )
        composable<TopLevelRoute.ActivityRoute>() {
            Text("Activity Screen")
        }
        composable<TopLevelRoute.LeaveRoute> {
            Text("Leave Screen")
        }
        composable<TopLevelRoute.ProfileRoute>() {
            Text("Profile Screen")
        }
    }
}
