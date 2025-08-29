package dev.fizcode.attendance.feature.dashboard.di

import dev.fizcode.attendance.feature.dashboard.domain.usecase.GetRealtimeClockUseCase
import dev.fizcode.attendance.feature.dashboard.presentation.DashboardViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun dashboardKoinModule() = module {
    includes(
        dashboardViewModelModule(),
        dashboardUseCaseModule()
    )
}

private fun dashboardViewModelModule() = module {
    viewModelOf(::DashboardViewModel)
}

private fun dashboardUseCaseModule() = module {
    singleOf(::GetRealtimeClockUseCase)
}
