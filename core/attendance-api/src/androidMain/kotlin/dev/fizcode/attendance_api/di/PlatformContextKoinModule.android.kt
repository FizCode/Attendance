package dev.fizcode.attendance_api.di

import dev.fizcode.attendance_api.util.ContextFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun platformContextKoinModule() = module {
    singleOf(::ContextFactory)
}
