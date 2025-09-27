package dev.fizcode.attendance_devicebinding.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_devicebinding.model.DeviceInfoModel
import dev.fizcode.attendance_devicebinding.model.DeviceResult
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.CoreCrypto.CC_SHA256
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding
import platform.UIKit.UIDevice

actual class DeviceInfoProvider {
    actual suspend fun getDeviceId(context: ContextFactory): DeviceResult<DeviceInfoModel> {
        val result = UIDevice.currentDevice.identifierForVendor?.UUIDString ?: ""
        return if (result.isBlank()) {
            DeviceResult.Error("Device ID not found")
        } else {
            DeviceResult.Success(
                DeviceInfoModel(
                    deviceId = sha256(result),
                    platform = UIDevice.currentDevice.systemName,
                    manufacturer = "Apple",
                    model = UIDevice.currentDevice.model,
                    osVersion = UIDevice.currentDevice.systemVersion
                )
            )
        }
    }

    @OptIn(BetaInteropApi::class, ExperimentalForeignApi::class)
    private fun sha256(input: String): String {
        val nsString: NSString = NSString.create(string = input)
        val data = nsString.dataUsingEncoding(NSUTF8StringEncoding) ?: return ""

        val digest = UByteArray(CC_SHA256_DIGEST_LENGTH)
        data.bytes?.let { bytes ->
            digest.usePinned { pinned ->
                CC_SHA256(bytes, data.length.toUInt(), pinned.addressOf(0))
            }
        }

        return digest.joinToString("") {
            it.toInt().toString(16).padStart(2, '0')
        }
    }
}
