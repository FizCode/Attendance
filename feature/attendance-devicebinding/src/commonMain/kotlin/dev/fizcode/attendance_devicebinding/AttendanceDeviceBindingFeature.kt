package dev.fizcode.attendance_devicebinding

import dev.fizcode.attendance_api.AttendanceFeature
import dev.fizcode.attendance_api.model.AttendanceResult
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_devicebinding.domain.DeviceIdProvider
import dev.fizcode.attendance_devicebinding.model.DeviceIdResult

class AttendanceDeviceBindingFeature(
    private val deviceIdProvider: DeviceIdProvider
) : AttendanceFeature {

    override val id: String = "device_binding"

    override suspend fun clockIn(context: ContextFactory): AttendanceResult<*> {
        return when (val result = deviceIdProvider.getDeviceId(context)) {
            is DeviceIdResult.Success -> {
                AttendanceResult.Data(result.value)
            }
            is DeviceIdResult.Error -> {
                AttendanceResult.Failure(result.message)
            }
        }
    }

    override suspend fun clockOut(context: ContextFactory): AttendanceResult<*> {
        return AttendanceResult.Data("Device binding feature")
    }
}
