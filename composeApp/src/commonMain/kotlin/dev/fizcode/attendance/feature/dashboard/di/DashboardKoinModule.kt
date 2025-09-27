package dev.fizcode.attendance.feature.dashboard.di

import dev.fizcode.attendance.feature.dashboard.domain.usecase.GetRealtimeClockUseCase
import dev.fizcode.attendance.feature.dashboard.presentation.DashboardViewModel
import dev.fizcode.attendance_api.AttendanceFeature
import dev.fizcode.attendance_api.AttendanceManager
import dev.fizcode.attendance_bometrics.AttendanceBiometricsFeature
import dev.fizcode.attendance_bometrics.di.biometricsModule
import dev.fizcode.attendance_devicebinding.AttendanceDeviceBindingFeature
import dev.fizcode.attendance_devicebinding.di.deviceBindingModule
import dev.fizcode.attendance_gps.AttendanceGpsFeature
import dev.fizcode.attendance_gps.di.gpsModule
import dev.fizcode.attendance_wifi.AttendanceWifiFeature
import dev.fizcode.attendance_wifi.di.wifiModule
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
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
    factoryOf(::GetRealtimeClockUseCase)
}

private fun dashboardAttendanceModule() = module {
    includes(biometricsModule())
    includes(deviceBindingModule())
    includes(gpsModule())
    includes(wifiModule())
    single {
        AttendanceManager(
            mapOf(
                "biometrics" to get<AttendanceFeature>(qualifier = named("biometrics")),
                "device_binding" to get<AttendanceFeature>(qualifier = named("device_binding")),
                "gps" to get<AttendanceFeature>(qualifier = named("gps")),
                "wifi" to get<AttendanceFeature>(qualifier = named("wifi"))
            )
        )
    }
    factory<AttendanceFeature>(named("biometrics")) {
        AttendanceBiometricsFeature(get())
    }
    single<AttendanceFeature>(named("device_binding")) {
        AttendanceDeviceBindingFeature(get())
    }
    single<AttendanceFeature>(named("gps")) {
        AttendanceGpsFeature(get())
    }
    factory<AttendanceFeature>(named("wifi")) {
        AttendanceWifiFeature(get())
    }
}
