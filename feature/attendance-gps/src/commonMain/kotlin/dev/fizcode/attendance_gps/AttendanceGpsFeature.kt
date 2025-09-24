package dev.fizcode.attendance_gps

import dev.fizcode.attendance_api.AttendanceFeature
import dev.fizcode.attendance_api.model.AttendanceResult
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_gps.domain.LocationProvider
import dev.fizcode.attendance_gps.model.LocationModel

class AttendanceGpsFeature(
    private val locationProvider: LocationProvider
) : AttendanceFeature {

    override val id: String = "gps"
    override suspend fun clockIn(context: ContextFactory): AttendanceResult<LocationModel> {
        if (locationProvider.hasLocationPermission(context = context)) {
            val location = locationProvider.getCurrentLocation(context = context)
            return AttendanceResult.Data(location)
        } else {
            val request = locationProvider.launchLocationPermissionRequest(context = context)
            return if (request) AttendanceResult.Failure("Please retry the operation")
            else AttendanceResult.Failure("Location permission denied")
        }
    }

    override suspend fun clockOut(context: ContextFactory): AttendanceResult<LocationModel> =
        AttendanceResult.NotAvailable

}
