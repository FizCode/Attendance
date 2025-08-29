package dev.fizcode.attendance.feature.dashboard.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.fizcode.attendance.core.designsystem.theme.bodyMediumEmphasized
import dev.fizcode.attendance.core.designsystem.theme.headlineMediumEmphasized
import dev.fizcode.attendance.core.designsystem.theme.titleMediumEmphasized
import dev.fizcode.attendance.feature.dashboard.presentation.model.ClockStatusUiModel
import dev.fizcode.attendance.feature.dashboard.presentation.model.DateTimeUiModel
import dev.fizcode.attendance.feature.dashboard.util.dummyClockStatus
import dev.fizcode.attendance.feature.dashboard.util.dummyDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ClockStatus(
    dateTime: DateTimeUiModel,
    clockStatus: ClockStatusUiModel
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.secondaryContainer),
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Date(
            dateTime = dateTime
        )
        StatusLabel(
            text = clockStatus.warning,
            backgroundColor = MaterialTheme.colorScheme.errorContainer,
            textColor = MaterialTheme.colorScheme.onErrorContainer
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusLabel(
                text = clockStatus.clockIn,
                modifier = Modifier.weight(1F),
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
            StatusLabel(
                text = clockStatus.clockOut,
                modifier = Modifier.weight(1F),
                backgroundColor = MaterialTheme.colorScheme.tertiary,
                textColor = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
    if (clockStatus.overtime.isNotBlank()) {
        Text(
            text = clockStatus.overtime,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun Date(
    dateTime: DateTimeUiModel
) = Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = dateTime.time,
        modifier = Modifier.weight(1F),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMediumEmphasized
    )
    Text(
        text = dateTime.day,
        modifier = Modifier.weight(1F),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )
    Column(
        modifier = Modifier.weight(1F),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = dateTime.dateMonth,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = dateTime.year,
            style = MaterialTheme.typography.bodyMediumEmphasized
        )
    }
}

@Composable
private fun StatusLabel(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    textColor: Color
) {
    if (text.isBlank()) return
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            color = textColor,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.StartEllipsis,
            style = MaterialTheme.typography.titleMediumEmphasized
        )
    }
}

@Composable
@Preview
private fun ClockStatusPreview() = ClockStatus(
    dateTime = dummyDateTime,
    clockStatus = dummyClockStatus
)
