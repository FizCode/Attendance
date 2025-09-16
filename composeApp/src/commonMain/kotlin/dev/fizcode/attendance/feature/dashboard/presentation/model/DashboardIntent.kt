package dev.fizcode.attendance.feature.dashboard.presentation.model

import dev.fizcode.attendance_api.util.ContextFactory

internal sealed interface DashboardIntent {
    data class OnNewsClick(val id: Int) : DashboardIntent
    data class OnArticleClick(val id: Int) : DashboardIntent
    data class ClockIn(val context: ContextFactory) : DashboardIntent
    data class ClockOut(val context: ContextFactory) : DashboardIntent
}
