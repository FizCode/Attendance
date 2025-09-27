package dev.fizcode.attendance_wifi.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import androidx.fragment.app.FragmentActivity
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_wifi.model.WifiInfoModel

@Suppress("DEPRECATION")
actual class WiFiInfoProvider {
    actual suspend fun getCurrentWifi(context: ContextFactory): WifiInfoModel? {
        val ctx = context.getActivity() as FragmentActivity
        val connectivityManager =
            ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiManager = ctx.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val network = connectivityManager.activeNetwork ?: return WifiInfoModel(ssid = "", bssid = "")
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        return if (capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            val wifiInfo = wifiManager.connectionInfo
            if (wifiInfo != null && wifiInfo.ssid != WifiManager.UNKNOWN_SSID) {
                WifiInfoModel(
                    ssid = wifiInfo.ssid.removePrefix("\"").removeSuffix("\""),
                    bssid = wifiInfo.bssid ?: ""
                )
            } else null
        } else {
            WifiInfoModel(
                ssid = "",
                bssid = ""
            )
        }
    }
}