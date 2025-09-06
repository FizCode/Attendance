package dev.fizcode.attendance.feature.dashboard.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.LocalPlatformContext
import dev.fizcode.attendance.core.common.extension.UiState
import dev.fizcode.attendance.core.designsystem.component.MediumArticle
import dev.fizcode.attendance.core.designsystem.component.MediumArticleShimmer
import dev.fizcode.attendance.core.designsystem.component.SimpleErrorTextButton
import dev.fizcode.attendance.core.designsystem.shape.roundedCornerMedium
import dev.fizcode.attendance.feature.dashboard.presentation.model.ArticleUiModel
import dev.fizcode.attendance.feature.dashboard.util.DashboardConstant
import dev.fizcode.attendance.feature.dashboard.util.dummyArticles
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun Articles(
    articles: UiState<List<ArticleUiModel>>,
    onArticleClick: (Int) -> Unit,
    context: PlatformContext,
    imageLoader: ImageLoader
) = Header(title = DashboardConstant.ARTICLES) {
    when (articles) {
        is UiState.Loading -> {
            repeat(5) {
                MediumArticleShimmer(
                    cornerRadius = roundedCornerMedium(
                        index = it,
                        lastIndex = 4
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        is UiState.Success -> {
            articles.data.forEachIndexed { index, article ->
                MediumArticle(
                    thumbnailUrl = article.thumbnail,
                    category = article.category,
                    title = article.title,
                    subtitle = article.subtitle,
                    context = context,
                    imageLoader = imageLoader,
                    cornerRadius = roundedCornerMedium(
                        index = index,
                        lastIndex = dummyArticles.lastIndex
                    ),
                    onArticleClick = { onArticleClick(index) }
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        else -> {
            SimpleErrorTextButton(
                message = "Something went wrong.",
                buttonText = DashboardConstant.RETRY,
                onButtonClick = { },
                modifier = Modifier
                    .height(104.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
private fun ArticlesPreview() {
    val context = LocalPlatformContext.current
    val imageLoader = remember { ImageLoader(context) }
    Articles(
        articles = UiState.Success(dummyArticles),
        onArticleClick = {},
        context = context,
        imageLoader = imageLoader
    )
}
