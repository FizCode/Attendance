package dev.fizcode.attendance.feature.dashboard.di

import dev.fizcode.attendance.feature.dashboard.domain.usecase.GetRealtimeClockUseCase
import dev.fizcode.attendance.feature.dashboard.presentation.DashboardViewModel
import dev.fizcode.attendance_api.AttendanceFeature
import dev.fizcode.attendance_bometrics.AttendanceBiometricsFeature
import dev.fizcode.attendance_bometrics.di.biometricsModule
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun dashboardKoinModule() = module {
    includes(
        dashboardViewModelModule(),
        dashboardUseCaseModule(),
        dashboardAttendanceModule()
    )
}

private fun dashboardViewModelModule() = module {
    viewModelOf(::DashboardViewModel)
}

private fun dashboardUseCaseModule() = module {
    singleOf(::GetRealtimeClockUseCase)
}

private fun dashboardAttendanceModule() = module {
    singleOf(::AttendanceBiometricsFeature) bind AttendanceFeature::class
    includes(biometricsModule())
}
