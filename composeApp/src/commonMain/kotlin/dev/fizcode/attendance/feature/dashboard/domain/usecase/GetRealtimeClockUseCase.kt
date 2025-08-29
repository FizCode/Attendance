package dev.fizcode.attendance.feature.dashboard.domain.usecase

import dev.fizcode.attendance.feature.dashboard.domain.model.DateTimeDomainModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class GetRealtimeClockUseCase {
    @OptIn(ExperimentalTime::class)
    operator fun invoke(): Flow<DateTimeDomainModel> = flow {
        while (true) {
            val now = Clock.System.now()
                .toLocalDateTime(TimeZone.Companion.currentSystemDefault())

            val model = DateTimeDomainModel(
                time = now.time,
                dayName = now.dayOfWeek,
                date = now.day,
                month = now.month,
                year = now.year
            )

            emit(model)

            val millisUntilNextMinute = (60 - now.second) * 1000L
            delay(millisUntilNextMinute)
        }
    }
}
