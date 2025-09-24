package dev.fizcode.attendance_bometrics.di

import dev.fizcode.attendance_bometrics.domain.BiometricAuthenticator
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

actual fun biometricsModule() = module {
    factoryOf(::BiometricAuthenticator)
}
