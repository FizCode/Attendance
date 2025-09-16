package dev.fizcode.attendance_bometrics.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_bometrics.model.BiometricResult

/**
 * Expected class for performing biometric authentication (e.g., fingerprint, face recognition).
 *
 * This class is declared in `commonMain` and has platform-specific implementations
 * (`actual class`) in `androidMain`, `iosMain`, or other targets.
 *
 * Usage:
 * ```
 * val authenticator = BiometricAuthenticator()
 * val result = authenticator.authenticate(contextFactory)
 * when (result) {
 *     BiometricResult.Success -> { /* proceed */ }
 *     BiometricResult.NotAvailable -> { /* show "not available" */ }
 *     is BiometricResult.Error -> { /* show error.message */ }
 *     BiometricResult.Failed -> { /* retry or handle failure */ }
 * }
 * ```
 */
expect class BiometricAuthenticator {

    /**
     * Performs biometric authentication on the current platform.
     *
     * @param context A [ContextFactory] providing the platform-specific context
     *                needed for biometric APIs (e.g., Activity on Android, LAContext on iOS).
     * @return A [BiometricResult] indicating whether authentication succeeded,
     *         failed, or was not available on the device.
     */
    suspend fun authenticate(context: ContextFactory): BiometricResult
}
