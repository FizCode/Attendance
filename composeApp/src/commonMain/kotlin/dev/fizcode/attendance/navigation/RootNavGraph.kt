package dev.fizcode.attendance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.fizcode.attendance.core.navigation.routes.RootRoute
import dev.fizcode.attendance.feature.login.navigation.loginNavGraph
import dev.fizcode.attendance.feature.toplevel.navigation.topLevelNavGraph

@Composable
fun RootNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = RootRoute.TopLevelRoute
    ) {
        topLevelNavGraph()
        loginNavGraph()
    }
}
