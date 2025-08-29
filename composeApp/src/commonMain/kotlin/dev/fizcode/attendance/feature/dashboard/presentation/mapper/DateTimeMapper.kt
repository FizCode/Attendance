package dev.fizcode.attendance.feature.dashboard.presentation.mapper

import dev.fizcode.attendance.feature.dashboard.domain.model.DateTimeDomainModel
import dev.fizcode.attendance.feature.dashboard.presentation.model.DateTimeUiModel

internal object DateTimeMapper {

    fun DateTimeDomainModel.mapToDateTimeUi(): DateTimeUiModel =
        DateTimeUiModel(
            time = "${this.time.hour.twoDigits()}:${this.time.minute.twoDigits()}",
            day = this.dayName.name.lowercase()
                .replaceFirstChar { it.titlecase() },
            dateMonth = "${this.date} ${
                this.month.name.lowercase()
                    .replaceFirstChar { it.titlecase() }.take(3)
            }",
            year = this.year.toString()
        )

    private fun Int.twoDigits(): String = this.toString().padStart(2, '0')
}
