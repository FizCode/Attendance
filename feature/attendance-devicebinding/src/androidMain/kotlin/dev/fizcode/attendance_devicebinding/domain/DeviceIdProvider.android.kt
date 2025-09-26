package dev.fizcode.attendance_devicebinding.domain

import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_devicebinding.model.DeviceInfoModel
import dev.fizcode.attendance_devicebinding.model.DeviceResult
import kotlinx.coroutines.suspendCancellableCoroutine
import java.security.MessageDigest
import kotlin.coroutines.resume

actual class DeviceInfoProvider {
    actual suspend fun getDeviceId(context: ContextFactory): DeviceResult<DeviceInfoModel> =
        suspendCancellableCoroutine { cont ->
            val ctxActivity = context.getActivity() as FragmentActivity
            try {
                val androidId = Settings.Secure.getString(
                    ctxActivity.contentResolver,
                    Settings.Secure.ANDROID_ID
                )

                val result = if (androidId.isNullOrBlank()) {
                    DeviceResult.Error("Android ID not found")
                } else {
                    println("Android Device: ${android.os.Build.DEVICE}")
                    DeviceResult.Success(
                        DeviceInfoModel(
                            deviceId = sha256(androidId),
                            platform = "Android",
                            manufacturer = android.os.Build.MANUFACTURER,
                            model = android.os.Build.MODEL,
                            osVersion = android.os.Build.VERSION.RELEASE
                        )
                    )
                }
                cont.resume(result)
            } catch (e: Exception) {
                cont.resume(DeviceResult.Error(e.message ?: "Unknown error"))
                return@suspendCancellableCoroutine
            }
        }

    private fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256")
            .digest(input.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}