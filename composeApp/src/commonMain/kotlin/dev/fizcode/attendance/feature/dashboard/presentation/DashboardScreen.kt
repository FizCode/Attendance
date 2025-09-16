package dev.fizcode.attendance.feature.dashboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import dev.fizcode.attendance.core.common.extension.UiState
import dev.fizcode.attendance.core.designsystem.toast.ToastDurationType
import dev.fizcode.attendance.core.designsystem.toast.ToastManager
import dev.fizcode.attendance.feature.dashboard.presentation.component.Articles
import dev.fizcode.attendance.feature.dashboard.presentation.component.ClockStatus
import dev.fizcode.attendance.feature.dashboard.presentation.component.DashboardExtendedFAB
import dev.fizcode.attendance.feature.dashboard.presentation.component.News
import dev.fizcode.attendance.feature.dashboard.presentation.model.DashboardEvent
import dev.fizcode.attendance.feature.dashboard.presentation.model.DashboardIntent
import dev.fizcode.attendance.feature.dashboard.presentation.model.DashboardState
import dev.fizcode.attendance.feature.dashboard.presentation.model.DateTimeUiModel
import dev.fizcode.attendance_api.util.ContextFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
internal fun DashboardScreen(
    platformContext: ContextFactory,
    topLevelNavPadding: PaddingValues,
    viewModel: DashboardViewModel = koinViewModel()
) {

    val time = viewModel.time.collectAsStateWithLifecycle()
    val dashboardState = viewModel.state.collectAsStateWithLifecycle()
    val toastManager by remember { mutableStateOf(ToastManager()) }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is DashboardEvent.BiometricSuccess -> {
                    toastManager.showToast(
                        contextFactory = platformContext,
                        message = "Biometric authentication successful",
                        toastDurationType = ToastDurationType.SHORT
                    )
                }

                is DashboardEvent.BiometricNotAvailable -> {
                    toastManager.showToast(
                        contextFactory = platformContext,
                        message = "Biometric authentication not available",
                        toastDurationType = ToastDurationType.SHORT
                    )
                }

                is DashboardEvent.BiometricFailure -> {
                    toastManager.showToast(
                        contextFactory = platformContext,
                        message = event.message,
                        toastDurationType = ToastDurationType.SHORT
                    )
                }
            }
        }
    }

    DashboardContent(
        time = time.value,
        dashboardState = dashboardState.value,
        onClickNews = { viewModel.handleIntent(DashboardIntent.OnNewsClick(it)) },
        onClickArticle = { viewModel.handleIntent(DashboardIntent.OnArticleClick(it)) },
        onClickClocIn = { viewModel.handleIntent(DashboardIntent.ClockIn(platformContext)) },
        onClickClockOut = { viewModel.handleIntent(DashboardIntent.ClockOut(platformContext)) },
        topLevelNavPadding = topLevelNavPadding
    )
}

@Composable
private fun DashboardContent(
    time: DateTimeUiModel,
    dashboardState: DashboardState,
    onClickNews: (Int) -> Unit,
    onClickArticle: (Int) -> Unit,
    onClickClocIn: () -> Unit,
    onClickClockOut: () -> Unit,
    topLevelNavPadding: PaddingValues,
) {

    val context = LocalPlatformContext.current
    val imageLoader = remember { ImageLoader(context) }
    val listState = rememberLazyListState()
    val expanded = remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = topLevelNavPadding.calculateBottomPadding())
            .padding(horizontal = 16.dp),
        contentWindowInsets = WindowInsets.systemBars,
        floatingActionButton = {
            val clockStatus = dashboardState.clockStatus
            if (clockStatus is UiState.Success) DashboardExtendedFAB(
                clockStatus = clockStatus.data.clockedStatus,
                onClickIn = onClickClocIn,
                onClickOut = onClickClockOut,
                expanded = expanded.value
            )
        }
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(topLevelNavPadding.calculateTopPadding()))
            }
            item {
                ClockStatus(
                    dateTime = time,
                    clockStatus = dashboardState.clockStatus
                )
            }
            item {
                News(
                    news = dashboardState.news,
                    onNewsClick = { newsId -> onClickNews(newsId) },
                    context = context,
                    imageLoader = imageLoader
                )
            }
            item {
                Articles(
                    articles = dashboardState.articles,
                    onArticleClick = { articleId -> onClickArticle(articleId) },
                    context = context,
                    imageLoader = imageLoader
                )
            }
        }
    }
}
