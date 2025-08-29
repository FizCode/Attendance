package dev.fizcode.attendance

import androidx.compose.ui.window.ComposeUIViewController
import dev.fizcode.attendance.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }