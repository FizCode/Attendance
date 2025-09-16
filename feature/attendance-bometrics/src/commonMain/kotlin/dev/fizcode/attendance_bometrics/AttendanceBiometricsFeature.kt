package dev.fizcode.attendance_bometrics

import dev.fizcode.attendance_api.AttendanceFeature
import dev.fizcode.attendance_api.model.AttendanceResult
import dev.fizcode.attendance_api.model.AttendanceResult.Failure
import dev.fizcode.attendance_api.model.AttendanceResult.NotAvailable
import dev.fizcode.attendance_api.model.AttendanceResult.Success
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_bometrics.domain.BiometricAuthenticator
import dev.fizcode.attendance_bometrics.model.BiometricResult

/**
 * An implementation of [AttendanceFeature] that uses biometric authentication
 * (e.g., fingerprint, face recognition) for clock-in and clock-out operations.
 *
 * This feature delegates authentication to a platform-specific [BiometricAuthenticator].
 * If the authentication is successful, the attendance action will be considered successful.
 * Otherwise, a corresponding [AttendanceResult.Failure] is returned with an error message.
 *
 * Example:
 * ```
 * val biometricFeature = AttendanceBiometricsFeature(authenticator)
 * val result = biometricFeature.clockIn(contextFactory)
 * if (result is AttendanceResult.Success) {
 *     // Proceed with clock-in
 * } else {
 *     // Handle failure (e.g., show error to user)
 * }
 * ```
 *
 * @property authenticator A platform-specific [BiometricAuthenticator] that performs
 * biometric authentication.
 */
class AttendanceBiometricsFeature(
    private val authenticator: BiometricAuthenticator
) : AttendanceFeature {

    /**
     * Unique identifier for the biometrics feature.
     * Used to distinguish this feature from others in a modular registry.
     */
    override val id = "biometrics"

    /**
     * Attempts to perform clock-in using biometric authentication.
     *
     * @param context A [ContextFactory] that provides platform-specific context
     *                required for biometric authentication.
     * @return [AttendanceResult.Success] if authentication succeeds,
     *         otherwise [AttendanceResult.Failure] with an error message.
     */
    override suspend fun clockIn(context: ContextFactory): AttendanceResult =
        when (val result = authenticator.authenticate(context = context)) {
            BiometricResult.Success -> Success

            BiometricResult.NotAvailable -> NotAvailable

            is BiometricResult.Error -> {
                Failure(result.message)
            }

            BiometricResult.Failed -> {
                Failure("Biometric authentication failed")
            }

        }

    /**
     * Attempts to perform clock-in using biometric authentication.
     *
     * @param context A [ContextFactory] that provides platform-specific context
     *                required for biometric authentication.
     * @return [AttendanceResult.Success] if authentication succeeds,
     *         otherwise [AttendanceResult.Failure] with an error message.
     */
    override suspend fun clockOut(context: ContextFactory): AttendanceResult =
        when (val result = authenticator.authenticate(context = context)) {
            BiometricResult.Success -> Success

            BiometricResult.NotAvailable -> NotAvailable

            is BiometricResult.Error -> {
                Failure("Biometric authentication failed: ${result.message}")
            }

            BiometricResult.Failed -> {
                Failure("Biometric authentication failed")
            }

        }
}
