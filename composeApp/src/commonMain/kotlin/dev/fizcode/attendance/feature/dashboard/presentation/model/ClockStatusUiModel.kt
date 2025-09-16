package dev.fizcode.attendance.feature.dashboard.presentation.model

internal data class ClockStatusUiModel(
    val warning: String,
    val clockedStatus: ClockedStatus,
    val clockIn: String,
    val clockOut: String,
    val overtime: String,
)
