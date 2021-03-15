package am.tatev.news.data.repository

import am.tatev.news.app.helpers.isNetworkAvailable
import am.tatev.news.data.api.NewsApiService
import am.tatev.news.data.db.NewsDao
import am.tatev.news.data.dto.Article
import am.tatev.news.data.dto.NewsResponse
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
        private val context: Context,
        private val remoteService: NewsApiService,
        private val localService: NewsDao
) {

    suspend fun fetchNews(): List<Article> =
        withContext(Dispatchers.IO) {
            try {
                if (context.isNetworkAvailable()) {
                    val response = fetchRemoteNews()
                    if (response.success) {
                        val news = response.metadata
                        localService.insertNewsIfNotExists(news)
                    }
                    else
                        fetchLocaleNews()
                } else {
                    fetchLocaleNews()
                }
            } catch (e: Exception) {
                fetchLocaleNews()
            }
        }

    suspend fun setArticleSeen(article: Article) {
        withContext(Dispatchers.IO) {
            localService.updateArticle(article)
        }
    }

    private suspend fun fetchRemoteNews(): NewsResponse =
        remoteService.fetchNews()

    private suspend fun fetchLocaleNews(): List<Article> =
        localService.news()

}
