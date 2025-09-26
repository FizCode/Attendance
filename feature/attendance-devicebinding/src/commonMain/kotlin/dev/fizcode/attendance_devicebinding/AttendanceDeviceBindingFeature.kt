package dev.fizcode.attendance_devicebinding

import dev.fizcode.attendance_api.AttendanceFeature
import dev.fizcode.attendance_api.model.AttendanceResult
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_devicebinding.domain.DeviceInfoProvider
import dev.fizcode.attendance_devicebinding.model.DeviceResult

class AttendanceDeviceBindingFeature(
    private val deviceInfoProvider: DeviceInfoProvider
) : AttendanceFeature {

    override val id: String = "device_binding"

    override suspend fun clockIn(context: ContextFactory): AttendanceResult<*> {
        return when (val result = deviceInfoProvider.getDeviceId(context)) {
            is DeviceResult.Success -> {
                AttendanceResult.Data(result.value)
            }
            is DeviceResult.Error -> {
                AttendanceResult.Failure(result.message)
            }
        }
    }

    override suspend fun clockOut(context: ContextFactory): AttendanceResult<*> {
        return AttendanceResult.Data("Device binding feature")
    }
}
