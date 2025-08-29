package dev.fizcode.attendance.feature.toplevel.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import dev.fizcode.attendance.feature.toplevel.TopLevelDestinationItem
import dev.fizcode.attendance.feature.toplevel.navigation.NavigationBarNavGraph
import dev.fizcode.attendance.feature.toplevel.presentation.component.NavigationBarComponent

@Composable
internal fun TopLevelScreen(
    navHostController: NavHostController,
) {
    val items = listOf(
        TopLevelDestinationItem.Dashboard,
        TopLevelDestinationItem.Activity,
        TopLevelDestinationItem.Leave,
        TopLevelDestinationItem.Profile
    )
    Scaffold(
        bottomBar = {
            NavigationBarComponent(
                navHostController = navHostController,
                items = items
            )
        }
    ) { innerPadding ->
        NavigationBarNavGraph(
            navHostController = navHostController,
            topLevelNavPadding = innerPadding
        )
    }
}
