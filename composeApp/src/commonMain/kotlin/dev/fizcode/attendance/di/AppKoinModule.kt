package dev.fizcode.attendance.di

import dev.fizcode.attendance.feature.dashboard.di.dashboardKoinModule
import dev.fizcode.attendance_api.di.attendanceApiKoinModule
import org.koin.dsl.module

fun appModule() = module {
    includes(
        featureModule(),
        coreModule()
    )
}

private fun featureModule() = module {
    includes(
        dashboardKoinModule()
    )
}

private fun coreModule() = module {
    includes(
        attendanceApiKoinModule()
    )
}
