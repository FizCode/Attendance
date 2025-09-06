package dev.fizcode.attendance.feature.dashboard.util

import dev.fizcode.attendance.feature.dashboard.presentation.model.ArticleUiModel
import dev.fizcode.attendance.feature.dashboard.presentation.model.ClockStatusUiModel
import dev.fizcode.attendance.feature.dashboard.presentation.model.ClockedStatus
import dev.fizcode.attendance.feature.dashboard.presentation.model.DateTimeUiModel
import dev.fizcode.attendance.feature.dashboard.presentation.model.NewsUiModel

internal val dummyDateTime = DateTimeUiModel(
    time = "00:00",
    day = "Saturday",
    dateMonth = "22 Aug",
    year = "2025"
)
internal val dummyClockStatus = ClockStatusUiModel(
    warning = "",
    clockedStatus = ClockedStatus.CLOCKED_OUT,
    clockIn = "08:12",
    clockOut = "18:18",
    overtime = "+1 hour overtime"
)

internal val dummyNews: List<NewsUiModel> = List(10) {
    NewsUiModel(
        id = it,
        thumbnail = "https://picsum.photos/199",
        authorAvatar = "https://picsum.photos/200",
        authorName = "John Doe",
        authorRole = "Content Writer",
        date = "22 Apr",
        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    )
}

internal val dummyArticles: List<ArticleUiModel> = listOf(
    ArticleUiModel(
        id = 1,
        thumbnail = "https://picsum.photos/201",
        category = "Weekly Report",
        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        subtitle = "Pellentesque sed consequat sapien. Nulla facilisi. Phasellus maximus dolor turpis, sed volutpat justo vestibulum non. Proin non faucibus nisi, at sodales ligula. Donec elementum nulla lorem, ut placerat erat cursus sed. Praesent quis turpis sed nisi interdum malesuada at ac urna."
    ),
    ArticleUiModel(
        id = 2,
        thumbnail = "https://picsum.photos/202",
        category = "Weekly Report",
        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        subtitle = "Pellentesque sed consequat sapien. Nulla facilisi. Phasellus maximus dolor turpis, sed volutpat justo vestibulum non. Proin non faucibus nisi, at sodales ligula. Donec elementum nulla lorem, ut placerat erat cursus sed. Praesent quis turpis sed nisi interdum malesuada at ac urna."
    ),
    ArticleUiModel(
        id = 3,
        thumbnail = "https://picsum.photos/203",
        category = "Weekly Report",
        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        subtitle = "Pellentesque sed consequat sapien. Nulla facilisi. Phasellus maximus dolor turpis, sed volutpat justo vestibulum non. Proin non faucibus nisi, at sodales ligula. Donec elementum nulla lorem, ut placerat erat cursus sed. Praesent quis turpis sed nisi interdum malesuada at ac urna."
    ),
    ArticleUiModel(
        id = 4,
        thumbnail = "https://picsum.photos/204",
        category = "Weekly Report",
        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        subtitle = "Pellentesque sed consequat sapien. Nulla facilisi. Phasellus maximus dolor turpis, sed volutpat justo vestibulum non. Proin non faucibus nisi, at sodales ligula. Donec elementum nulla lorem, ut placerat erat cursus sed. Praesent quis turpis sed nisi interdum malesuada at ac urna."
    ),
    ArticleUiModel(
        id = 5,
        thumbnail = "https://picsum.photos/205",
        category = "Weekly Report",
        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        subtitle = "Pellentesque sed consequat sapien. Nulla facilisi. Phasellus maximus dolor turpis, sed volutpat justo vestibulum non. Proin non faucibus nisi, at sodales ligula. Donec elementum nulla lorem, ut placerat erat cursus sed. Praesent quis turpis sed nisi interdum malesuada at ac urna."
    )
)
