package dev.fizcode.attendance.feature.dashboard.presentation.model

internal sealed interface DashboardEvent {
    data object BiometricSuccess : DashboardEvent
    data object BiometricNotAvailable : DashboardEvent
    data class BiometricFailure(val message: String) : DashboardEvent
}
