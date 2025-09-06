package dev.fizcode.attendance.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import dev.fizcode.attendance.core.designsystem.shape.shimmerBrush
import dev.fizcode.attendance.core.designsystem.theme.bodySmallEmphasized
import dev.fizcode.attendance.core.designsystem.theme.titleMediumEmphasized
import dev.fizcode.attendance.feature.dashboard.util.dummyArticles
import dev.fizcode.attendance.feature.dashboard.util.dummyNews
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LargeArticle(
    thumbnailUrl: String,
    author: String,
    authorImage: String,
    authorRole: String,
    date: String,
    title: String,
    modifier: Modifier = Modifier,
    context: PlatformContext,
    imageLoader: ImageLoader,
    cornerRadius: RoundedCornerShape = RoundedCornerShape(8.dp),
    onArticleClick: () -> Unit
) = Column(
    modifier = modifier
        .clip(cornerRadius)
        .clickable { onArticleClick }
        .width(280.dp)
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(4.dp)
) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(thumbnailUrl)
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "Article Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(120.dp)
            .background(shimmerBrush())
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(authorImage)
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "Article Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(28.dp)
                .background(shimmerBrush())
        )
        Column(
            modifier = Modifier.weight(1F),
        ) {
            Text(
                text = author,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = authorRole,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = date,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodySmall
        )
    }
    Text(
        text = title,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleMediumEmphasized,
        maxLines = 2,
        minLines = 2,
        overflow = TextOverflow.Ellipsis
    )

}

@Composable
fun LargeArticleShimmer(
    cornerRadius: RoundedCornerShape = RoundedCornerShape(8.dp),
) = Column(
    modifier = Modifier.clip(cornerRadius)
        .width(280.dp)
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(4.dp)
) {
    val shimmer = shimmerBrush()
    val rounded4 = RoundedCornerShape(4.dp)
    Box(
        modifier = Modifier
            .clip(cornerRadius)
            .height(120.dp)
            .fillMaxWidth()
            .background(shimmer)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(28.dp)
                .background(shimmer)
        )
        Column(
            modifier = Modifier.weight(1F),
        ) {
            Box(
                modifier = Modifier
                    .clip(rounded4)
                    .height(18.dp)
                    .width(86.dp)
                    .background(shimmer)
            )
            Spacer(Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .clip(rounded4)
                    .height(16.dp)
                    .width(100.dp)
                    .background(shimmer)
            )
        }
        Box(
            modifier = Modifier
                .clip(rounded4)
                .height(16.dp)
                .width(38.dp)
                .background(shimmer)
        )
    }
    Box(
        modifier = Modifier
            .clip(rounded4)
            .height(22.dp)
            .fillMaxWidth()
            .background(shimmer)
    )
    Box(
        modifier = Modifier
            .clip(rounded4)
            .height(22.dp)
            .width(200.dp)
            .background(shimmer)
    )
}

@Composable
fun MediumArticle(
    thumbnailUrl: String,
    category: String,
    title: String,
    subtitle: String,
    context: PlatformContext,
    imageLoader: ImageLoader,
    cornerRadius: RoundedCornerShape = RoundedCornerShape(8.dp),
    onArticleClick: () -> Unit
) = Row(
    modifier = Modifier
        .clip(cornerRadius)
        .clickable { onArticleClick }
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .padding(8.dp),
    horizontalArrangement = Arrangement.spacedBy(6.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(thumbnailUrl)
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "Article Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .height(88.dp)
            .width(80.dp)
            .background(shimmerBrush())
    )
    Column {
        Text(
            text = category,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmallEmphasized
        )
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMediumEmphasized,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = subtitle,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun MediumArticleShimmer(
    cornerRadius: RoundedCornerShape = RoundedCornerShape(8.dp),
) = Row(
    modifier = Modifier
        .clip(cornerRadius)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .padding(8.dp),
    horizontalArrangement = Arrangement.spacedBy(6.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    val shimmer = shimmerBrush()
    val rounded4 = RoundedCornerShape(4.dp)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .height(88.dp)
            .width(80.dp)
            .background(shimmer)
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(rounded4)
                .height(16.dp)
                .width(84.dp)
                .background(shimmer)
        )
        Box(
            modifier = Modifier
                .clip(rounded4)
                .height(24.dp)
                .width(100.dp)
                .background(shimmer)
        )
        Box(
            modifier = Modifier
                .clip(rounded4)
                .height(14.dp)
                .fillMaxWidth()
                .background(shimmer)
        )
        Box(
            modifier = Modifier
                .clip(rounded4)
                .height(14.dp)
                .width(250.dp)
                .background(shimmer)
        )
        Box(
            modifier = Modifier
                .clip(rounded4)
                .height(14.dp)
                .width(160.dp)
                .background(shimmer)
        )
    }
}

@Composable
@Preview
private fun ArticlePreview() = Column {
    val context = LocalPlatformContext.current
    val imageLoader = remember { ImageLoader(context) }
    val news = dummyNews[0]
    val article = dummyArticles[0]
    LargeArticle(
        thumbnailUrl = news.thumbnail,
        authorImage = news.authorAvatar,
        author = news.authorName,
        authorRole = news.authorRole,
        date = news.date,
        title = news.title,
        context = context,
        imageLoader = imageLoader,
        onArticleClick = {}
    )
    Spacer(modifier = Modifier.height(16.dp))
    LargeArticleShimmer()
    Spacer(modifier = Modifier.height(16.dp))
    MediumArticle(
        thumbnailUrl = article.thumbnail,
        category = article.category,
        title = article.title,
        subtitle = article.subtitle,
        context = context,
        imageLoader = imageLoader,
        onArticleClick = {}
    )
    Spacer(modifier = Modifier.height(16.dp))
    MediumArticleShimmer()
}
