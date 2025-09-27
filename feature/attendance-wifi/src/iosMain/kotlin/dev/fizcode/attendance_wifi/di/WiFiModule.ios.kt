package dev.fizcode.attendance_wifi.di

import dev.fizcode.attendance_wifi.domain.WiFiInfoProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual fun wifiModule() = module {
    factoryOf(::WiFiInfoProvider)
}