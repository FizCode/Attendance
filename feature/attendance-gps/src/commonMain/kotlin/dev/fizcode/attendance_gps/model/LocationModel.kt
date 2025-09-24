package dev.fizcode.attendance_gps.model

data class LocationModel(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float? = null
)
