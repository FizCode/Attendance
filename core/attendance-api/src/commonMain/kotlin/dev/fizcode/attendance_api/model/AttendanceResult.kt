package dev.fizcode.attendance_api.model

/**
 * Represents the result of an attendance operation, such as `clockIn` or `clockOut`.
 *
 * This sealed class allows handling different outcomes (success, failure, unavailable feature,
 * or success with additional data) in a type-safe manner.
 *
 * Usage:
 * ```
 * val result: AttendanceResult = attendanceFeature.clockIn(context)
 * when (result) {
 *     is AttendanceResult.Success -> { /* Proceed with workflow */ }
 *     is AttendanceResult.Data -> { /* Handle extra result data */ }
 *     is AttendanceResult.Failure -> { /* Show error message */ }
 *     is AttendanceResult.NotAvailable -> { /* Show fallback UI or disable feature */ }
 * }
 * ```
 */
sealed class AttendanceResult<out T> {

    /**
     * Indicates that the attendance action was completed successfully,
     * but does not carry any additional data.
     */
    object Success : AttendanceResult<Nothing>()

    /**
     * Represents a successful attendance action that returns additional data.
     *
     * Unlike [Success], which only signals completion, this result carries
     * a payload of type [T] that can contain extra information specific to
     * the feature or operation performed.
     *
     * This is useful for operations where the outcome is not just a simple
     * "success" or "failure", but also includes meaningful data that the
     * application may need to process further.
     *
     * Example use cases:
     * - Returning a scanned QR code string.
     * - Providing GPS coordinates for a location-based attendance.
     * - Delivering the recognized user ID from a biometric face scan.
     * - Returning NFC tag content.
     *
     * Usage:
     * ```
     * val result: AttendanceResult<String> = AttendanceResult.Data("QR_CODE_CONTENT")
     * when (result) {
     *     is AttendanceResult.Data -> {
     *         val qrCode = result.value
     *         // Process QR code
     *     }
     *     else -> { /* Handle other cases */ }
     * }
     * ```
     *
     * @param value The payload of type [T] containing the result data.
     */
    data class Data<out T>(val value: T) : AttendanceResult<T>()

    /**
     * Indicates that the attendance action failed.
     *
     * Use this to represent operational errors (e.g., authentication failure,
     * network issue, invalid input) that occurred during the attendance process.
     *
     * @property message A human-readable error message describing why the
     * operation failed (e.g., "Biometric authentication failed").
     */
    data class Failure(val message: String) : AttendanceResult<Nothing>()

    /**
     * Indicates that the attendance action could not be performed because
     * the required feature is not available on the device.
     *
     * Example cases include:
     * - The device does not support biometrics.
     * - GPS hardware is missing or disabled.
     * - NFC is not supported on the current platform.
     * - Necessary permissions are denied.
     *
     * This allows the application to distinguish between an operational error
     * ([Failure]) and a fundamental lack of capability ([NotAvailable]).
     */
    object NotAvailable : AttendanceResult<Nothing>()
}
