package dev.fizcode.attendance_devicebinding.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_devicebinding.model.DeviceIdResult

expect class DeviceIdProvider {
    suspend fun getDeviceId(context: ContextFactory): DeviceIdResult<*>
}
