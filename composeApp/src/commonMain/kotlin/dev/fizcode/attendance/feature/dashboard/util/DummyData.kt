package dev.fizcode.attendance.feature.dashboard.util

import dev.fizcode.attendance.feature.dashboard.presentation.model.ClockStatusUiModel
import dev.fizcode.attendance.feature.dashboard.presentation.model.DateTimeUiModel

internal val dummyDateTime = DateTimeUiModel(
    time = "00:00",
    day = "Saturday",
    dateMonth = "22 Aug",
    year = "2025"
)
internal val dummyClockStatus = ClockStatusUiModel(
    warning = "",
    clockIn = "",
    clockOut = "",
    overtime = ""
)
