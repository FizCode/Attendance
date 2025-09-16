package dev.fizcode.attendance_bometrics.di

import dev.fizcode.attendance_bometrics.domain.BiometricAuthenticator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun biometricsModule() = module {
    singleOf(::BiometricAuthenticator)
}
