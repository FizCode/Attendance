package dev.fizcode.attendance.feature.toplevel.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.fizcode.attendance.feature.toplevel.TopLevelDestinationItem

@Composable
internal fun NavigationBarComponent(
    navHostController: NavHostController,
    items: List<TopLevelDestinationItem>
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
        ?: TopLevelDestinationItem.Dashboard.route

    NavigationBar {
        items.forEach { item ->

            val isSelected by remember(currentDestination) {
                derivedStateOf { currentDestination == item.route::class.qualifiedName }
            }

            AddItem(
                label = {
                    Text(text = item.label)
                },
                unselectedIcon = {
                    Icon(imageVector = item.unselectedIcon, contentDescription = item.label)
                },
                selectedIcon = {
                    Icon(imageVector = item.selectedIcon, contentDescription = item.label)
                },
                selected = isSelected,
                onClick = {
                    navHostController.navigate(item.route) {
                        popUpTo(navHostController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.AddItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    alwaysShowLabel: Boolean = true,
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    onClick: () -> Unit
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else unselectedIcon,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            indicatorColor = MaterialTheme.colorScheme.primary,
        )
    )
}
