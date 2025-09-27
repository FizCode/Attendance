package dev.fizcode.attendance_wifi.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_wifi.model.WifiInfoModel
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.autoreleasepool
import platform.CoreFoundation.CFStringRef
import platform.Foundation.NSDictionary
import platform.SystemConfiguration.CNCopyCurrentNetworkInfo
import platform.SystemConfiguration.CNCopySupportedInterfaces
import platform.SystemConfiguration.kCNNetworkInfoKeyBSSID
import platform.SystemConfiguration.kCNNetworkInfoKeySSID

actual class WiFiInfoProvider {

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    actual suspend fun getCurrentWifi(context: ContextFactory): WifiInfoModel? = autoreleasepool {
        val supportedInterfaces = CNCopySupportedInterfaces() as? List<*>
        if (supportedInterfaces.isNullOrEmpty()) {
            return@autoreleasepool WifiInfoModel(ssid = "", bssid = "")
        }

        for (interfaceName in supportedInterfaces) {
            val cfName = interfaceName as? CFStringRef ?: continue
            val info = CNCopyCurrentNetworkInfo(cfName) as? NSDictionary ?: continue
            val ssid = info.objectForKey(kCNNetworkInfoKeySSID) as? String
            val bssid = info.objectForKey(kCNNetworkInfoKeyBSSID) as? String

            if (!ssid.isNullOrBlank() || !bssid.isNullOrBlank()) {
                return@autoreleasepool WifiInfoModel(
                    ssid = ssid?.trim('"') ?: "",
                    bssid = bssid ?: ""
                )
            }
        }

        WifiInfoModel(ssid = "", bssid = "")
    }
}
