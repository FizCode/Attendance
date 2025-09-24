package dev.fizcode.attendance_gps.domain

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.LocationServices
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_gps.model.LocationModel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class LocationProvider {

    actual fun hasLocationPermission(context: ContextFactory): Boolean {
        val activity = context.getActivity() as FragmentActivity
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        return ContextCompat.checkSelfPermission(
            activity,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    actual fun launchLocationPermissionRequest(context: ContextFactory): Boolean {
        val activity = context.getActivity() as FragmentActivity
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        ActivityCompat.requestPermissions(activity, arrayOf(permission), 1001)
        return hasLocationPermission(context = context)
    }

    actual suspend fun getCurrentLocation(context: ContextFactory): LocationModel =
        suspendCoroutine { cont ->
            val activity = context.getActivity() as FragmentActivity
            if (!hasLocationPermission(context)) {
                cont.resumeWithException(SecurityException("Location permission not granted"))
                return@suspendCoroutine
            }
            val fused = LocationServices.getFusedLocationProviderClient(activity)
            try {
                fused.lastLocation.addOnSuccessListener { loc ->
                    if (loc != null) {
                        cont.resume(
                            LocationModel(
                                latitude = loc.latitude,
                                longitude = loc.longitude,
                                accuracy = loc.accuracy
                            )
                        )
                    } else {
                        cont.resumeWithException(IllegalStateException("Location not available"))
                    }
                }.addOnFailureListener { e ->
                    cont.resumeWithException(e)
                }
            } catch (se: SecurityException) {
                cont.resumeWithException(se)
            }
        }
}
