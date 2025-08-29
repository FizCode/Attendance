package dev.fizcode.attendance.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

// Display
val Typography.displayLargeEmphasized: TextStyle
    get() = displayLarge.copy(fontWeight = FontWeight.Bold)

val Typography.displayMediumEmphasized: TextStyle
    get() = displayMedium.copy(fontWeight = FontWeight.Bold)

val Typography.displaySmallEmphasized: TextStyle
    get() = displaySmall.copy(fontWeight = FontWeight.Bold)

// Headline
val Typography.headlineLargeEmphasized: TextStyle
    get() = headlineLarge.copy(fontWeight = FontWeight.Bold)

val Typography.headlineMediumEmphasized: TextStyle
    get() = headlineMedium.copy(fontWeight = FontWeight.Bold)

val Typography.headlineSmallEmphasized: TextStyle
    get() = headlineSmall.copy(fontWeight = FontWeight.Bold)

// Title
val Typography.titleLargeEmphasized: TextStyle
    get() = titleLarge.copy(fontWeight = FontWeight.Bold)

val Typography.titleMediumEmphasized: TextStyle
    get() = titleMedium.copy(fontWeight = FontWeight.Bold)

val Typography.titleSmallEmphasized: TextStyle
    get() = titleSmall.copy(fontWeight = FontWeight.Bold)

// Body
val Typography.bodyLargeEmphasized: TextStyle
    get() = bodyLarge.copy(fontWeight = FontWeight.Medium)

val Typography.bodyMediumEmphasized: TextStyle
    get() = bodyMedium.copy(fontWeight = FontWeight.Medium)

val Typography.bodySmallEmphasized: TextStyle
    get() = bodySmall.copy(fontWeight = FontWeight.Medium)

// Label
val Typography.labelLargeEmphasized: TextStyle
    get() = labelLarge.copy(fontWeight = FontWeight.Medium)

val Typography.labelMediumEmphasized: TextStyle
    get() = labelMedium.copy(fontWeight = FontWeight.Medium)

val Typography.labelSmallEmphasized: TextStyle
    get() = labelSmall.copy(fontWeight = FontWeight.Medium)
