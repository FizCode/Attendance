package dev.fizcode.attendance_wifi.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_wifi.model.WifiInfoModel

expect class WiFiInfoProvider {
    suspend fun getCurrentWifi(context: ContextFactory): WifiInfoModel?
}
