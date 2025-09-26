package dev.fizcode.attendance_devicebinding.model

sealed class DeviceIdResult<out T> {
    data class Success<out T>(val value: T) : DeviceIdResult<T>()
    data class Error(val message: String) : DeviceIdResult<Nothing>()
}
