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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import dev.fizcode.attendance.core.common.extension.UiState
import dev.fizcode.attendance.feature.dashboard.presentation.component.Articles
import dev.fizcode.attendance.feature.dashboard.presentation.component.ClockStatus
import dev.fizcode.attendance.feature.dashboard.presentation.component.News
import dev.fizcode.attendance.feature.dashboard.presentation.model.ClockedStatus
import dev.fizcode.attendance.feature.dashboard.util.DashboardConstant
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
internal fun DashboardScreen(
    topLevelNavPadding: PaddingValues,
    viewModel: DashboardViewModel = koinViewModel()
) {

    val time = viewModel.time.collectAsStateWithLifecycle()
    val dashboardState = viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val expanded = remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }
    val context = LocalPlatformContext.current
    val imageLoader = remember { ImageLoader(context) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = topLevelNavPadding.calculateBottomPadding())
            .padding(horizontal = 16.dp),
        contentWindowInsets = WindowInsets.systemBars,
        floatingActionButton = {
            val clockStatus = dashboardState.value.clockStatus
            if (clockStatus is UiState.Success) DashboardExtendedFAB(
                clockStatus = clockStatus.data.clockedStatus,
                onClick = { },
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
                    dateTime = time.value,
                    clockStatus = dashboardState.value.clockStatus
                )
            }
            item {
                News(
                    news = dashboardState.value.news,
                    onNewsClick = {},
                    context = context,
                    imageLoader = imageLoader
                )
            }
            item {
                Articles(
                    articles = dashboardState.value.articles,
                    onArticleClick = {},
                    context = context,
                    imageLoader = imageLoader
                )
            }
        }
    }
}

@Composable
private fun DashboardExtendedFAB(
    clockStatus: ClockedStatus,
    onClick: () -> Unit,
    expanded: Boolean,
) = when (clockStatus) {
    ClockedStatus.NOT_CLOCKED -> {
        ExtendedFAB(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            expanded = expanded,
            text = DashboardConstant.CLOCK_IN
        )
    }

    ClockedStatus.CLOCKED_IN -> {
        ExtendedFAB(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            expanded = expanded,
            text = DashboardConstant.CLOCK_OUT
        )
    }

    else -> {
        // No FAB if CLOCKED_OUT and DISABLED
    }
}

@Composable
private fun ExtendedFAB(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    expanded: Boolean,
    text: String,
) = ExtendedFloatingActionButton(
    onClick = onClick,
    containerColor = containerColor,
    contentColor = contentColor,
    expanded = expanded,
    icon = {
        Icon(
            imageVector = Icons.Filled.Fingerprint,
            contentDescription = DashboardConstant.CLOCK_ICON
        )
    },
    text = { Text(text) }
)
