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
 * If the authentication succeeds, the attendance action is considered successful.
 * Otherwise, a corresponding failure result is returned:
 * - [AttendanceResult.NotAvailable] if the device does not support biometric authentication.
 * - [AttendanceResult.Failure] with an error message if authentication fails or an error occurs.
 *
 * The generic type parameter `<T>` is not used in this implementation since biometric
 * attendance does not produce additional data. All results are effectively
 * [AttendanceResult]<Nothing>, but casted to [AttendanceResult]<T> for interface compatibility.
 *
 * Example:
 * ```
 * val biometricFeature = AttendanceBiometricsFeature(authenticator)
 * val result = biometricFeature.clockIn(contextFactory)
 * when (result) {
 *     is AttendanceResult.Success -> {
 *         // Proceed with clock-in
 *     }
 *     is AttendanceResult.NotAvailable -> {
 *         // Device does not support biometric authentication
 *     }
 *     is AttendanceResult.Failure -> {
 *         // Handle error (e.g., show message to user)
 *     }
 * }
 * ```
 *
 * @property authenticator A platform-specific [BiometricAuthenticator] responsible
 * for performing biometric authentication.
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
     *         [AttendanceResult.NotAvailable] if biometrics are not supported,
     *         or [AttendanceResult.Failure] with an error message if authentication fails.
     */
    override suspend fun clockIn(context: ContextFactory): AttendanceResult<*> =
        when (val result = authenticator.authenticate(context = context)) {
            is BiometricResult.Success -> Success

            is BiometricResult.NotAvailable -> NotAvailable

            is BiometricResult.Error -> {
                Failure(result.message)
            }

            is BiometricResult.Failed -> {
                Failure("Biometric authentication failed")
            }

        }

    /**
     * Attempts to perform clock-out using biometric authentication.
     *
     * @param context A [ContextFactory] that provides platform-specific context
     *                required for biometric authentication.
     * @return [AttendanceResult.Success] if authentication succeeds,
     *         [AttendanceResult.NotAvailable] if biometrics are not supported,
     *         or [AttendanceResult.Failure] with an error message if authentication fails.
     */
    override suspend fun clockOut(context: ContextFactory): AttendanceResult<*> =
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
