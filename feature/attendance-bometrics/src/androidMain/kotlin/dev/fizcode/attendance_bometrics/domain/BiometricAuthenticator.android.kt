package dev.fizcode.attendance_bometrics.domain

import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import dev.fizcode.attendance_api.util.ContextFactory
import dev.fizcode.attendance_bometrics.model.BiometricResult
import dev.fizcode.attendance_bometrics.util.Constant
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Provides biometric authentication using Android's [BiometricPrompt].
 *
 * This class is the Android actual implementation of a multiplatform `BiometricAuthenticator`.
 * It launches a biometric prompt and returns the authentication result in a suspending way.
 *
 * ## Usage flow:
 * 1. Call [authenticate] with a [ContextFactory] that can provide a [FragmentActivity].
 * 2. If biometric hardware and enrollment is available, a biometric prompt will be shown.
 * 3. The result of authentication is returned as a [BiometricResult].
 *
 * Supported results:
 * - [BiometricResult.Success] → Authentication succeeded
 * - [BiometricResult.Error] → An error occurred (with message)
 * - [BiometricResult.Failed] → Authentication failed (e.g., bad fingerprint)
 * - [BiometricResult.NotAvailable] → Biometric is not available on the device
 *
 * This implementation uses [suspendCancellableCoroutine] to integrate with Kotlin coroutines,
 * ensuring proper cancellation handling.
 */
actual class BiometricAuthenticator() {

    /**
     * Launches the biometric authentication flow.
     *
     * @param context A [ContextFactory] providing the current [FragmentActivity].
     * @return [BiometricResult] describing the outcome of authentication.
     *
     * Possible return values:
     * - [BiometricResult.Success] if authentication succeeds.
     * - [BiometricResult.Error] if an unrecoverable error occurs.
     * - [BiometricResult.Failed] if authentication fails without an error.
     * - [BiometricResult.NotAvailable] if biometric authentication is not supported.
     *
     * This function suspends until authentication finishes or is cancelled.
     */
    actual suspend fun authenticate(context: ContextFactory): BiometricResult =
        suspendCancellableCoroutine { cont ->

            val ctx = context.getActivity() as? FragmentActivity ?: run {
                cont.resume(
                    BiometricResult.Error(
                        Constant.MSG_INVALID_CONTEXT
                    )
                )
                return@suspendCancellableCoroutine
            }

            if (isBiometricAvailable(ctx)) {
                ctx.lifecycleScope.launch {
                    val result = showBiometricPrompt(ctx)
                    if (cont.isActive) cont.resume(result)
                }
            } else {
                if (cont.isActive) {
                    cont.resume(BiometricResult.NotAvailable)
                }
            }
        }

    /**
     * Checks if biometric authentication is available on the device.
     *
     * @param ctx The current [FragmentActivity].
     * @return `true` if biometric authentication can be attempted, `false` otherwise.
     *
     * On Android R (API 30) and above, it checks for both BIOMETRIC_STRONG and DEVICE_CREDENTIAL.
     * On lower versions, it falls back to BIOMETRIC_WEAK.
     */
    private fun isBiometricAvailable(ctx: FragmentActivity): Boolean {
        val biometricManager = BiometricManager.from(ctx)
        val authenticators = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
        } else {
            BiometricManager.Authenticators.BIOMETRIC_WEAK
        }

        return when (biometricManager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_SUCCESS,
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED,
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> true

            else -> false
        }
    }

    /**
     * Displays the biometric prompt and waits for the authentication result.
     *
     * @param ctx The [FragmentActivity] used to host the prompt.
     * @return [BiometricResult] representing the outcome of authentication.
     *
     * This function is suspendable and integrates with cancellation.
     * If the coroutine is cancelled, the biometric prompt is also dismissed.
     *
     * Internally, it uses [BiometricPrompt] with a callback for handling authentication events.
     */
    private suspend fun showBiometricPrompt(ctx: FragmentActivity): BiometricResult =
        suspendCancellableCoroutine { continuation ->
            val executor = ContextCompat.getMainExecutor(ctx)

            val biometricPrompt = BiometricPrompt(
                ctx,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        if (continuation.isActive) {
                            continuation.resume(BiometricResult.Success)
                        }
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        if (continuation.isActive) {
                            continuation.resume(
                                BiometricResult.Error(
                                    errString.toString()
                                )
                            )
                        }
                    }

                    override fun onAuthenticationFailed() {
                        if (continuation.isActive) {
                            continuation.resume(BiometricResult.Failed)
                        }
                    }
                }
            )

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(Constant.PROMPT_TITLE)
                .setSubtitle(Constant.PROMPT_SUBTITLE)
                .setNegativeButtonText(Constant.PROMPT_NEGATIVE_TEXT)
                .build()

            biometricPrompt.authenticate(promptInfo)

            continuation.invokeOnCancellation {
                biometricPrompt.cancelAuthentication()
            }
        }
}
