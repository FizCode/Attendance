package dev.fizcode.attendance_devicebinding.model

sealed class DeviceResult<out T> {
    data class Success<out T>(val value: T) : DeviceResult<T>()
    data class Error(val message: String) : DeviceResult<Nothing>()
}
