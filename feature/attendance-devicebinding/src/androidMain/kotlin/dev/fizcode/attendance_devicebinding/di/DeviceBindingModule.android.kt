package dev.fizcode.attendance_devicebinding.di

import dev.fizcode.attendance_devicebinding.domain.DeviceInfoProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun deviceBindingModule() = module {
    singleOf(::DeviceInfoProvider)
}
