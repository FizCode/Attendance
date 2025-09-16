package dev.fizcode.attendance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import dev.fizcode.attendance_api.util.ContextFactory

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                platformContext = ContextFactory(this)
            )
        }
    }
}
@Composable
@Preview
fun AppPreview() {
    val platformContext = LocalContext.current
    App(
        platformContext = ContextFactory(platformContext as ComponentActivity)
    )
}
