package dev.fizcode.attendance_gps.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_gps.model.LocationModel
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class LocationProvider {

    private val manager = CLLocationManager()
    private val delegate = SingleShotDelegate()

    init {
        manager.delegate = delegate
    }

    actual fun hasLocationPermission(context: ContextFactory): Boolean {
        val status = CLLocationManager.authorizationStatus()
        return status == kCLAuthorizationStatusAuthorizedWhenInUse || status == kCLAuthorizationStatusAuthorizedAlways
    }

    actual fun launchLocationPermissionRequest(context: ContextFactory): Boolean {
        manager.requestWhenInUseAuthorization()
        return hasLocationPermission(context)
    }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun getCurrentLocation(context: ContextFactory): LocationModel =
        suspendCoroutine { cont ->
            delegate.start(cont)
            manager.requestLocation()
        }

    private class SingleShotDelegate : NSObject(), CLLocationManagerDelegateProtocol {
        private var continuation: Continuation<LocationModel>? = null

        fun start(cont: Continuation<LocationModel>) {
            continuation?.resumeWithException(IllegalStateException("Location request already in progress"))
            continuation = cont
        }

        @OptIn(ExperimentalForeignApi::class)
        override fun locationManager(
            manager: CLLocationManager,
            didUpdateLocations: List<*>
        ) {
            val cont = continuation ?: return
            continuation = null
            val loc = didUpdateLocations.lastOrNull() as? CLLocation
            if (loc != null) {
                val coordinate = loc.coordinate
                val result = coordinate.useContents {
                    LocationModel(
                        latitude = latitude,
                        longitude = longitude,
                        accuracy = loc.horizontalAccuracy.toFloat()
                    )
                }
                cont.resume(result)
            } else {
                cont.resumeWithException(IllegalStateException("Location not available"))
            }
        }

        override fun locationManager(
            manager: CLLocationManager,
            didFailWithError: NSError
        ) {
            val cont = continuation ?: return
            continuation = null
            cont.resumeWithException(Exception(didFailWithError.localizedDescription))
        }
    }
}
