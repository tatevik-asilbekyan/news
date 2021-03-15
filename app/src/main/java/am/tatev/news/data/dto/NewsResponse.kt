package am.tatev.news.data.dto

data class NewsResponse(
    val success: Boolean,
    val errors: List<String>,
    val metadata: List<Article>
)
