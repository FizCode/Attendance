package dev.fizcode.attendance.feature.toplevel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsWalk
import androidx.compose.material.icons.automirrored.rounded.DirectionsRun
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.rounded.HistoryEdu
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ManageAccounts
import androidx.compose.ui.graphics.vector.ImageVector
import dev.fizcode.attendance.core.navigation.routes.TopLevelRoute

internal sealed class TopLevelDestinationItem(
    val label: String,
    val route: TopLevelRoute,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Dashboard : TopLevelDestinationItem(
        label = "Dashboard",
        route = TopLevelRoute.DashboardRoute,
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home
    )
    data object Activity : TopLevelDestinationItem(
        label = "Activity",
        route = TopLevelRoute.ActivityRoute,
        selectedIcon = Icons.Rounded.HistoryEdu,
        unselectedIcon = Icons.Outlined.HistoryEdu
    )
    data object Leave : TopLevelDestinationItem(
        label = "Leave",
        route = TopLevelRoute.LeaveRoute,
        selectedIcon = Icons.AutoMirrored.Rounded.DirectionsRun,
        unselectedIcon = Icons.AutoMirrored.Outlined.DirectionsWalk
    )
    data object Profile : TopLevelDestinationItem(
        label = "Profile",
        route = TopLevelRoute.ProfileRoute,
        selectedIcon = Icons.Rounded.ManageAccounts,
        unselectedIcon = Icons.Outlined.ManageAccounts
    )
}
