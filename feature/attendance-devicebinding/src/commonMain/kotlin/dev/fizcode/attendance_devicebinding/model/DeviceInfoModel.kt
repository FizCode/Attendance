package dev.fizcode.attendance_devicebinding.model

data class DeviceInfoModel(
    val deviceId: String,
    val platform: String,
    val manufacturer: String,
    val model: String,
    val osVersion: String
)
