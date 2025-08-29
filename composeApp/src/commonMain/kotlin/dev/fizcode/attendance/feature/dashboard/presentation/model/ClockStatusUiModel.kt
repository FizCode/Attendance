package dev.fizcode.attendance.feature.dashboard.presentation.model

data class ClockStatusUiModel(
    val warning: String,
    val clockIn: String,
    val clockOut: String,
    val overtime: String,
)
