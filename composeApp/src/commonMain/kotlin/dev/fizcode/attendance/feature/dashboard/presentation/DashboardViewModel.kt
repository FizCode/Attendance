package dev.fizcode.attendance.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fizcode.attendance.core.common.extension.UiState
import dev.fizcode.attendance.feature.dashboard.domain.usecase.GetRealtimeClockUseCase
import dev.fizcode.attendance.feature.dashboard.presentation.mapper.DateTimeMapper.mapToDateTimeUi
import dev.fizcode.attendance.feature.dashboard.presentation.model.DashboardState
import dev.fizcode.attendance.feature.dashboard.presentation.model.DateTimeUiModel
import dev.fizcode.attendance.feature.dashboard.util.dummyArticles
import dev.fizcode.attendance.feature.dashboard.util.dummyClockStatus
import dev.fizcode.attendance.feature.dashboard.util.dummyNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class DashboardViewModel(
    private val useCase: GetRealtimeClockUseCase
) : ViewModel() {

    private val _time = MutableStateFlow(DateTimeUiModel())
    val time: StateFlow<DateTimeUiModel> = _time.onStart {
        loadTime()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = DateTimeUiModel()
    )
    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.onStart {
        loadAll()
    }.flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = DashboardState()
        )

    private suspend fun loadAll() = coroutineScope {
        val clockStatusDeferred = async { loadClockStatus() }
        val newsDeferred = async { loadNews() }
        val articlesDeferred = async { loadArticles() }
        delay(1000)
        awaitAll(
            clockStatusDeferred,
            newsDeferred,
            articlesDeferred
        )
    }

    private fun loadTime() = viewModelScope.launch(Dispatchers.IO) {
        useCase().collect { _time.value = it.mapToDateTimeUi() }
    }

    private fun loadClockStatus() {
        _state.value = _state.value.copy(
            clockStatus = UiState.Success(dummyClockStatus)
        )
    }

    private fun loadNews() {
        _state.value = _state.value.copy(
            news = UiState.Success(dummyNews)
        )
    }

    private fun loadArticles() {
        _state.value = _state.value.copy(
            articles = UiState.Success(dummyArticles)
        )
    }

}
