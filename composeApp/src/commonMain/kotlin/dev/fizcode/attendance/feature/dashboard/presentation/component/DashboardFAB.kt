package dev.fizcode.attendance.feature.dashboard.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.fizcode.attendance.feature.dashboard.presentation.model.ClockedStatus
import dev.fizcode.attendance.feature.dashboard.util.DashboardConstant

@Composable
internal fun DashboardExtendedFAB(
    clockStatus: ClockedStatus,
    onClickIn: () -> Unit,
    onClickOut: () -> Unit,
    expanded: Boolean,
) = when (clockStatus) {
    ClockedStatus.NOT_CLOCKED -> {
        ExtendedFAB(
            onClick = onClickIn,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            expanded = expanded,
            text = DashboardConstant.CLOCK_IN
        )
    }

    ClockedStatus.CLOCKED_IN -> {
        ExtendedFAB(
            onClick = onClickOut,
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            expanded = expanded,
            text = DashboardConstant.CLOCK_OUT
        )
    }

    else -> {
        // No FAB if CLOCKED_OUT and DISABLED
    }
}

@Composable
private fun ExtendedFAB(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    expanded: Boolean,
    text: String,
) = ExtendedFloatingActionButton(
    onClick = onClick,
    containerColor = containerColor,
    contentColor = contentColor,
    expanded = expanded,
    icon = {
        Icon(
            imageVector = Icons.Filled.Fingerprint,
            contentDescription = DashboardConstant.CLOCK_ICON
        )
    },
    text = { Text(text) }
)
