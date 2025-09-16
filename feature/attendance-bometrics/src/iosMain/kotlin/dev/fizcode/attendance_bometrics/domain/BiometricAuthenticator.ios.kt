package dev.fizcode.attendance_bometrics.domain

import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_bometrics.model.BiometricResult
import dev.fizcode.attendance_bometrics.util.Constant
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import platform.LocalAuthentication.LAContext
import platform.LocalAuthentication.LAPolicyDeviceOwnerAuthenticationWithBiometrics
import kotlin.coroutines.resume

/**
 * Provides biometric authentication using iOS LocalAuthentication framework.
 *
 * This class is the iOS actual implementation of a multiplatform `BiometricAuthenticator`.
 * It uses [LAContext] and [evaluatePolicy] to authenticate the user with biometrics
 * (Face ID or Touch ID, depending on the device).
 *
 * ## Usage flow:
 * 1. Call [authenticate] with a [ContextFactory].
 * 2. If biometric hardware and enrollment is available, the system authentication dialog will be shown.
 * 3. The result of authentication is returned as a [BiometricResult].
 *
 * Supported results:
 * - [BiometricResult.Success] → Authentication succeeded
 * - [BiometricResult.Error] → An error occurred (with message from NSError)
 * - [BiometricResult.NotAvailable] → Biometric authentication is not available
 *
 * This implementation uses [suspendCancellableCoroutine] to integrate with Kotlin coroutines,
 * ensuring proper cancellation handling.
 */
actual class BiometricAuthenticator() {

    /**
     * Launches the biometric authentication flow using iOS LocalAuthentication APIs.
     *
     * @param context A [ContextFactory] (not directly used in iOS, but required for API symmetry).
     * @return [BiometricResult] describing the outcome of authentication.
     *
     * Possible return values:
     * - [BiometricResult.Success] if authentication succeeds.
     * - [BiometricResult.Error] if an unrecoverable error occurs, with details from [NSError].
     * - [BiometricResult.NotAvailable] if biometric authentication cannot be evaluated.
     *
     * This function suspends until authentication finishes or fails.
     */
    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun authenticate(context: ContextFactory): BiometricResult =
        suspendCancellableCoroutine { continuation ->
            val laContext = LAContext()
            val canEval = laContext.canEvaluatePolicy(
                LAPolicyDeviceOwnerAuthenticationWithBiometrics,
                null
            )
            if (!canEval) {
                continuation.resume(
                    BiometricResult.NotAvailable
                )
                return@suspendCancellableCoroutine
            }

            laContext.evaluatePolicy(
                LAPolicyDeviceOwnerAuthenticationWithBiometrics,
                localizedReason = Constant.PROMPT_TITLE
            ) { success, error: NSError? ->
                if (success) {
                    continuation.resume(
                        BiometricResult.Success
                    )
                } else {
                    continuation.resume(
                        BiometricResult.Error(
                            error?.localizedDescription
                                ?: Constant.MSG_FAILED
                        )
                    )
                }
            }
        }
}
