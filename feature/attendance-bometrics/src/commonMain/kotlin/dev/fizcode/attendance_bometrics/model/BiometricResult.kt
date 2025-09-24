package dev.fizcode.attendance_bometrics.model

/**
 * Represents the possible outcomes of a biometric authentication attempt.
 *
 * This sealed class is used as a result wrapper for biometric authentication flows
 * across platforms (Android/iOS). It ensures a strongly typed, exhaustive set of outcomes.
 */
sealed class BiometricResult {

    /**
     * Authentication succeeded and the user has been verified.
     */
    data object Success : BiometricResult()

    /**
     * Biometric authentication is not available on the device
     * (e.g., no hardware, no enrolled biometrics, or unsupported OS version).
     */
    data object NotAvailable : BiometricResult()

    /**
     * An unrecoverable error occurred during authentication.
     *
     * @property message A human-readable description of the error.
     */
    data class Error(val message: String) : BiometricResult()

    /**
     * Authentication failed due to invalid biometric input
     * (e.g., wrong fingerprint or face not recognized).
     * The user may retry.
     */
    data object Failed : BiometricResult()
}
