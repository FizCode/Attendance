package dev.fizcode.attendance.core.designsystem.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun roundedCornerMedium(index: Int, lastIndex: Int): RoundedCornerShape = when (index) {
    0 -> RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomEnd = 4.dp,
        bottomStart = 4.dp
    )

    lastIndex -> RoundedCornerShape(
        topStart = 4.dp,
        topEnd = 4.dp,
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    )

    else -> RoundedCornerShape(2.dp)
}
