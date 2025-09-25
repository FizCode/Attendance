package dev.fizcode.attendance_gps.di

import dev.fizcode.attendance_gps.domain.LocationProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun gpsModule() = module {
    singleOf(::LocationProvider)
}
