package dev.fizcode.attendance

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dev.fizcode.attendance.core.designsystem.theme.AttendanceTheme
import dev.fizcode.attendance.navigation.RootNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AttendanceTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            RootNavGraph(
                navController = rememberNavController()
            )
        }
    }
}