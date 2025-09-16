package dev.fizcode.attendance

import androidx.compose.ui.window.ComposeUIViewController
import dev.fizcode.attendance.di.initKoin
import dev.fizcode.attendance_api.util.ContextFactory

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) {
    App(
        platformContext = ContextFactory()
    )
}