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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.fizcode.attendance.feature.dashboard.presentation.component.ClockStatus
import dev.fizcode.attendance.feature.dashboard.presentation.component.Header
import dev.fizcode.attendance.feature.dashboard.presentation.component.LargeArticle
import dev.fizcode.attendance.feature.dashboard.presentation.component.MediumArticle
import dev.fizcode.attendance.feature.dashboard.util.dummyClockStatus
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
internal fun DashboardScreen(
    topLevelNavPadding: PaddingValues,
    viewModel: DashboardViewModel = koinViewModel()
) {

    val time = viewModel.time.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val expanded = remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }
    val isNotClockedIn = dummyClockStatus.clockIn.isBlank()
    val isClockedIn = (dummyClockStatus.clockIn.isNotBlank() && dummyClockStatus.clockOut.isBlank())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = topLevelNavPadding.calculateBottomPadding())
            .padding(horizontal = 16.dp),
        contentWindowInsets = WindowInsets.systemBars,
        floatingActionButton = {
            when {
                isNotClockedIn -> {
                    ExtendedFloatingActionButton(
                        onClick = { },
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        expanded = expanded.value,
                        icon = { Icon(Icons.Filled.Fingerprint, "Floating action button.") },
                        text = { Text("Clock In") }

                    )
                }
                isClockedIn -> {
                    ExtendedFloatingActionButton(
                        onClick = { },
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary,
                        expanded = expanded.value,
                        icon = { Icon(Icons.Filled.Fingerprint, "Floating action button.") },
                        text = { Text("Clock Out") }

                    )
                }
            }
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
                    clockStatus = dummyClockStatus
                )
            }
            item {
                Header(title = "News") {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(3) {
                            LargeArticle()
                        }
                    }
                }
            }
            item {
                Header(title = "Articles") {
                    repeat(10) {
                        MediumArticle()
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}
