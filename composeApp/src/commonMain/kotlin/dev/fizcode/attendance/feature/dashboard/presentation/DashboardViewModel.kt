package dev.fizcode.attendance.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fizcode.attendance.feature.dashboard.domain.usecase.GetRealtimeClockUseCase
import dev.fizcode.attendance.feature.dashboard.presentation.mapper.DateTimeMapper.mapToDateTimeUi
import dev.fizcode.attendance.feature.dashboard.presentation.model.DateTimeUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
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

    private val _time = MutableStateFlow(DateTimeUiModel("--:--", "", "", ""))
    val time: StateFlow<DateTimeUiModel> = _time.onStart {
        loadTime()
    }.flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = DateTimeUiModel("--:--", "", "", "")
        )

    private fun loadTime() = viewModelScope.launch(Dispatchers.IO) {
        useCase().collect { _time.value = it.mapToDateTimeUi() }
    }

}
