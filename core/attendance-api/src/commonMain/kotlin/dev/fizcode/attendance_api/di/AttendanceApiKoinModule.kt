package dev.fizcode.attendance_api.di

import org.koin.dsl.module

fun attendanceApiKoinModule() = module {
    includes(
        platformContextKoinModule()
    )
}
