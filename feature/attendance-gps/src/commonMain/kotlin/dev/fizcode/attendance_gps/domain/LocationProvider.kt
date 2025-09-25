package dev.fizcode.attendance_gps.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_gps.model.LocationModel

expect class LocationProvider {
    fun hasLocationPermission(context: ContextFactory): Boolean
    fun launchLocationPermissionRequest(context: ContextFactory): Boolean
    suspend fun getCurrentLocation(context: ContextFactory): LocationModel
}
