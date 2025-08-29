package dev.fizcode.attendance.feature.dashboard.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.fizcode.attendance.core.designsystem.theme.bodySmallEmphasized
import dev.fizcode.attendance.core.designsystem.theme.titleMediumEmphasized
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun LargeArticle() = Column(
    modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .clickable {  }
        .width(280.dp)
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(4.dp)
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(180.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(28.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        )
        Column(
            modifier = Modifier.weight(1F),
        ) {
            Text(
                text = "John Doe",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "Content Writer",
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = "22 Apr",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodySmall
        )
    }
    Text(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleMediumEmphasized,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )

}

@Composable
internal fun MediumArticle() = Row(
    modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .clickable {  }
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .padding(8.dp),
    horizontalArrangement = Arrangement.spacedBy(6.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .size(80.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
    )
    Column {
        Text(
            text = "Weekly Report",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmallEmphasized
        )
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMediumEmphasized,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Pellentesque sed consequat sapien. Nulla facilisi. Phasellus maximus dolor turpis, sed volutpat justo vestibulum non. Proin non faucibus nisi, at sodales ligula. Donec elementum nulla lorem, ut placerat erat cursus sed. Praesent quis turpis sed nisi interdum malesuada at ac urna.",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
private fun ArticlePreview() = Column {
    LargeArticle()
    Spacer(modifier = Modifier.height(16.dp))
    MediumArticle()
}
