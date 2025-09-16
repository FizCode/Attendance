package dev.fizcode.attendance.core.designsystem.toast

import dev.fizcode.attendance_api.util.ContextFactory

expect open class ToastManager() {
    fun showToast(
        contextFactory: ContextFactory,
        message: String,
        toastDurationType: ToastDurationType
    )
}
