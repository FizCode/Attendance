package dev.fizcode.attendance.feature.dashboard.presentation.model

internal data class NewsUiModel(
    val id: Int,
    val thumbnail: String,
    val authorAvatar: String,
    val authorName: String,
    val authorRole: String,
    val date: String,
    val title: String,
)
