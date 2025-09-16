package dev.fizcode.attendance.feature.dashboard.domain.model

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

data class DateTimeDomainModel(
    val time: LocalTime,
    val dayName: DayOfWeek,
    val date: Int,
    val month: Month,
    val year: Int
)
