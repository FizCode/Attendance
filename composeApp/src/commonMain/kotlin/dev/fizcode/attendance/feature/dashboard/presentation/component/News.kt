package dev.fizcode.attendance.feature.dashboard.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.LocalPlatformContext
import dev.fizcode.attendance.core.common.extension.UiState
import dev.fizcode.attendance.core.designsystem.component.LargeArticle
import dev.fizcode.attendance.core.designsystem.component.LargeArticleShimmer
import dev.fizcode.attendance.core.designsystem.component.SimpleErrorTextButton
import dev.fizcode.attendance.feature.dashboard.presentation.model.NewsUiModel
import dev.fizcode.attendance.feature.dashboard.util.DashboardConstant
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun News(
    news: UiState<List<NewsUiModel>>,
    onNewsClick: (Int) -> Unit,
    context: PlatformContext,
    imageLoader: ImageLoader
) = Header(title = DashboardConstant.NEWS) {
    when (news) {
        is UiState.Loading -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) {
                    LargeArticleShimmer()
                }
            }
        }

        is UiState.Success -> {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = news.data,
                    key = { it.id }
                ) {
                    LargeArticle(
                        thumbnailUrl = it.thumbnail,
                        authorImage = it.authorAvatar,
                        author = it.authorName,
                        authorRole = it.authorRole,
                        date = it.date,
                        title = it.title,
                        context = context,
                        imageLoader = imageLoader,
                        onArticleClick = { onNewsClick(it.id) }
                    )
                }
            }
        }

        else -> {
            SimpleErrorTextButton(
                message = "Something went wrong.",
                buttonText = "Retry",
                onButtonClick = { },
                modifier = Modifier
                    .height(228.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
private fun NewsPreview() {
    val context = LocalPlatformContext.current
    val imageLoader = remember { ImageLoader(context) }
    News(
        news = UiState.Empty,
        onNewsClick = {},
        context = context,
        imageLoader = imageLoader

    )
}
