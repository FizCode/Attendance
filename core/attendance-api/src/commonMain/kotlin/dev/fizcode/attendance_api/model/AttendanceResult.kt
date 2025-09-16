package dev.fizcode.attendance_api.model

/**
 * Represents the result of an attendance operation, such as `clockIn` or `clockOut`.
 *
 * This sealed class allows handling both successful and failed attendance attempts
 * in a type-safe manner.
 *
 * Usage:
 * ```
 * val result: AttendanceResult = attendanceFeature.clockIn(context)
 * when (result) {
 *     is AttendanceResult.Success -> { /* Proceed with workflow */ }
 *     is AttendanceResult.Failure -> { /* Show error message */ }
 *     is AttendanceResult.NotAvailable -> { /* Show fallback UI or disable feature */ }
 * }
 * ```
 */
sealed class AttendanceResult {

    /**
     * Indicates that the attendance action was completed successfully.
     */
    object Success : AttendanceResult()

    /**
     * Indicates that the attendance action failed.
     *
     * @property message A human-readable error message describing why the
     *                   operation failed (e.g., "Biometric not available").
     */
    data class Failure(val message: String) : AttendanceResult()

    /**
     * Indicates that the attendance action could not be performed because
     * the required feature is not available on the device.
     *
     * Example cases include:
     * - The device does not support biometrics.
     * - Necessary hardware or permissions are missing.
     *
     * This allows the application to distinguish between an operational error
     * (`Failure`) and a fundamental lack of capability (`NotAvailable`).
     */
    object NotAvailable : AttendanceResult()
}
