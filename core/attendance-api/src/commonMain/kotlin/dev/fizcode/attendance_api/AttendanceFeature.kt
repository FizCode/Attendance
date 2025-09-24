package dev.fizcode.attendance_api

import dev.fizcode.attendance_api.model.AttendanceResult
import dev.fizcode.attendance_api.util.ContextFactory

/**
 * Represents a modular attendance feature in the application.
 *
 * Each implementation of [AttendanceFeature] defines its own
 * method of handling user attendance (e.g., biometric, QR code, location).
 *
 * The [id] property is a unique identifier for the feature,
 * allowing it to be distinguished, registered, and retrieved dynamically
 * in a modular/plugin-like system.
 *
 * Example usage:
 * ```
 * class BiometricAttendanceFeature : AttendanceFeature {
 *     override val id: String = "biometric"
 *
 *     override suspend fun clockIn(context: ContextFactory): AttendanceResult {
 *         // handle biometric clock-in
 *     }
 *
 *     override suspend fun clockOut(context: ContextFactory): AttendanceResult {
 *         // handle biometric clock-out
 *     }
 * }
 * ```
 *
 * Typical use cases for [id]:
 * - To register multiple attendance features in a map or registry
 * - To load a specific feature dynamically based on configuration or user preference
 * - To identify the feature in logging, analytics, or debugging
 *
 * @property id A unique string identifier for the feature (e.g., "biometric", "qrcode", "location").
 */
interface AttendanceFeature {

    /**
     * A unique identifier for the attendance feature.
     *
     * This identifier is used to distinguish between multiple
     * implementations and enable dynamic lookup or configuration.
     */
    val id: String

    /**
     * Handles the clock-in process for attendance.
     *
     * @param context A [ContextFactory] providing platform-specific context required by the feature.
     * @return An [AttendanceResult] representing the outcome of the clock-in operation.
     */
    suspend fun clockIn(context: ContextFactory): AttendanceResult<*>

    /**
     * Handles the clock-out process for attendance.
     *
     * @param context A [ContextFactory] providing platform-specific context required by the feature.
     * @return An [AttendanceResult] representing the outcome of the clock-out operation.
     */
    suspend fun clockOut(context: ContextFactory): AttendanceResult<*>

}
