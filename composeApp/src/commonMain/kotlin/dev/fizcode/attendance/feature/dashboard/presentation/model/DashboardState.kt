package dev.fizcode.attendance.feature.dashboard.presentation.model

import dev.fizcode.attendance.core.common.extension.UiState

internal data class DashboardState(
    val clockStatus: UiState<ClockStatusUiModel> = UiState.Loading,
    val news: UiState<List<NewsUiModel>> = UiState.Loading,
    val articles: UiState<List<ArticleUiModel>> = UiState.Loading
)
