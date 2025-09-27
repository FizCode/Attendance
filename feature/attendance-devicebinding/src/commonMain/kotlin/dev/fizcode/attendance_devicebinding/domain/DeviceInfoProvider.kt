package dev.fizcode.attendance_devicebinding.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_devicebinding.model.DeviceInfoModel
import dev.fizcode.attendance_devicebinding.model.DeviceResult

expect class DeviceInfoProvider {
    suspend fun getDeviceId(context: ContextFactory): DeviceResult<DeviceInfoModel>
}
