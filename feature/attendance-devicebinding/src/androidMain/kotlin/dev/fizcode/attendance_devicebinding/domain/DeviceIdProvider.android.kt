package dev.fizcode.attendance_devicebinding.domain

import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_devicebinding.model.DeviceIdResult
import kotlinx.coroutines.suspendCancellableCoroutine
import java.security.MessageDigest
import kotlin.coroutines.resume

actual class DeviceIdProvider {
    actual suspend fun getDeviceId(context: ContextFactory): DeviceIdResult<*> =
        suspendCancellableCoroutine { cont ->
            val ctxActivity = context.getActivity() as FragmentActivity
            try {
                val androidId = Settings.Secure.getString(
                    ctxActivity.contentResolver,
                    Settings.Secure.ANDROID_ID
                )

                val result = if (androidId.isNullOrBlank()) {
                    DeviceIdResult.Error("Android ID not found")
                } else {
                    DeviceIdResult.Success(sha256(androidId))
                }
                cont.resume(result)
            } catch (e: Exception) {
                cont.resume(DeviceIdResult.Error(e.message ?: "Unknown error"))
                return@suspendCancellableCoroutine
            }
        }

    private fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256")
            .digest(input.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}