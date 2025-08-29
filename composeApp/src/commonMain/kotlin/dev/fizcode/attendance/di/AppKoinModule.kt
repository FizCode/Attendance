package dev.fizcode.attendance.di

import dev.fizcode.attendance.feature.dashboard.di.dashboardKoinModule
import org.koin.dsl.module

fun appModule() = module {
    includes(
        featureModule()
    )
}

private fun featureModule() = module {
    includes(
        dashboardKoinModule()
    )
}
