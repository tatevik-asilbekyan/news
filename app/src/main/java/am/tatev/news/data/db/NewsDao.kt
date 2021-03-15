package am.tatev.news.data.db

import am.tatev.news.data.dto.Article
import androidx.room.*

@Dao
interface NewsDao {

    @Query("SELECT * FROM article ORDER BY date DESC")
    suspend fun news(): List<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: List<Article>)

    @Transaction
    suspend fun insertNewsIfNotExists(news: List<Article>): List<Article> {
        val localNews = news().toMutableList()
        news.subtract(localNews).takeIf {
            it.isNotEmpty()
        }?.filter { diffItem ->
            !localNews.any {
                it.date == diffItem.date
            }
        }?.also {
            insertNews(it)
            localNews.addAll(it)
        }

        return localNews
    }

    @Update
    suspend fun updateArticle(article: Article)

}