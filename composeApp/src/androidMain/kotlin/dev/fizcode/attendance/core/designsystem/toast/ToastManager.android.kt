package dev.fizcode.attendance.core.designsystem.toast

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import dev.fizcode.attendance_api.util.ContextFactory

actual open class ToastManager actual constructor() {
    actual fun showToast(
        contextFactory: ContextFactory,
        message: String,
        toastDurationType: ToastDurationType
    ) {
        val context = contextFactory.getActivity() as FragmentActivity
        val duration = when (toastDurationType) {
            ToastDurationType.SHORT -> Toast.LENGTH_SHORT
            ToastDurationType.LONG -> Toast.LENGTH_LONG
        }
        Toast.makeText(context, message, duration).show()
    }
}