package dev.fizcode.attendance.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fizcode.attendance.core.common.extension.UiState
import dev.fizcode.attendance.feature.dashboard.domain.usecase.GetRealtimeClockUseCase
import dev.fizcode.attendance.feature.dashboard.presentation.mapper.DateTimeMapper.mapToDateTimeUi
import dev.fizcode.attendance.feature.dashboard.presentation.model.DashboardEvent
import dev.fizcode.attendance.feature.dashboard.presentation.model.DashboardIntent
import dev.fizcode.attendance.feature.dashboard.presentation.model.DashboardState
import dev.fizcode.attendance.feature.dashboard.presentation.model.DateTimeUiModel
import dev.fizcode.attendance.feature.dashboard.util.dummyArticles
import dev.fizcode.attendance.feature.dashboard.util.dummyClockStatus
import dev.fizcode.attendance.feature.dashboard.util.dummyNews
import dev.fizcode.attendance_api.AttendanceManager
import dev.fizcode.attendance_api.model.AttendanceResult
import dev.fizcode.attendance_api.util.ContextFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class DashboardViewModel(
    private val attendanceManager: AttendanceManager,
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

    private val _event = MutableSharedFlow<DashboardEvent>()
    val event = _event.asSharedFlow()

    fun handleIntent(intent: DashboardIntent) = when (intent) {
        is DashboardIntent.OnArticleClick -> {}
        is DashboardIntent.OnNewsClick -> {}
        is DashboardIntent.ClockIn -> onClickIn(intent.context)
        is DashboardIntent.ClockOut -> onClickOut(intent.context)
    }

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

    private fun onClickIn(context: ContextFactory) = viewModelScope.launch(Dispatchers.IO) {
        shouldShowBiometrics(context)
    }

    private fun onClickOut(context: ContextFactory) = viewModelScope.launch(Dispatchers.IO) {
        shouldShowBiometrics(context)
    }

    private fun shouldShowBiometrics(context: ContextFactory) = viewModelScope.launch {
        val features = listOf("biometrics", "gps")
        val biometricResult = attendanceManager.execute(
            featureIds = features,
            context = context
        )
        biometricResult.forEach { (id, result) ->
            when (result) {
                is AttendanceResult.Success -> {
                    if (id == "biometrics") {
                        _event.emit(DashboardEvent.BiometricSuccess)
                    }
                }

                is AttendanceResult.Data<*> -> {
                    if (id == "gps") {
                        println("Updated GPS Location -> ${result.value}")
                    }
                }

                is AttendanceResult.Failure -> {
                    if (id == "biometrics") {
                        _event.emit(DashboardEvent.BiometricFailure(result.message))
                    }
                }

                is AttendanceResult.NotAvailable -> {
                    if (id == "biometrics") {
                        _event.emit(DashboardEvent.BiometricNotAvailable)
                    }
                }
            }
        }

    }

}
