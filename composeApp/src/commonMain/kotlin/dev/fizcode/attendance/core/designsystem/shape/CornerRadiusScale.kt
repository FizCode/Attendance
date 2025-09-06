package dev.fizcode.attendance.core.designsystem.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun roundedCornerMedium(index: Int, lastIndex: Int): RoundedCornerShape = when (index) {
    0 -> RoundedCornerShape(
        topStart = 12.dp,
        topEnd = 12.dp,
        bottomEnd = 2.dp,
        bottomStart = 2.dp
    )

    lastIndex -> RoundedCornerShape(
        topStart = 2.dp,
        topEnd = 2.dp,
        bottomStart = 12.dp,
        bottomEnd = 12.dp
    )

    else -> RoundedCornerShape(2.dp)
}
