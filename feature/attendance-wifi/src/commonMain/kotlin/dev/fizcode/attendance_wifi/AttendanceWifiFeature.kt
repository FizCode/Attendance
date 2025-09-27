package dev.fizcode.attendance_wifi

import dev.fizcode.attendance_api.AttendanceFeature
import dev.fizcode.attendance_api.model.AttendanceResult
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_wifi.domain.WiFiInfoProvider

class AttendanceWifiFeature(
    private val wifiInfoProvider: WiFiInfoProvider
) : AttendanceFeature {

    override val id: String = "wifi"

    override suspend fun clockIn(context: ContextFactory): AttendanceResult<*> {
        val result = wifiInfoProvider.getCurrentWifi(context)
        println("AttendanceWifiFeature.Wifi -> $result")
        return AttendanceResult.Data(result)
    }

    override suspend fun clockOut(context: ContextFactory): AttendanceResult<*> {
        return AttendanceResult.NotAvailable
    }
}