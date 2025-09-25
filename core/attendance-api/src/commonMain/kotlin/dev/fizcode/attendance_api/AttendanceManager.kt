package dev.fizcode.attendance_api

import dev.fizcode.attendance_api.model.AttendanceResult
import dev.fizcode.attendance_api.util.ContextFactory

class AttendanceManager(
    private val features: Map<String, AttendanceFeature>
) {
    suspend fun execute(
        featureIds: List<String>,
        context: ContextFactory
    ): Map<String, AttendanceResult<*>> {
        return featureIds.associateWith { id ->
            features[id]?.clockIn(context)
                ?: AttendanceResult.Failure("Feature $id not found")
        }
    }
}
