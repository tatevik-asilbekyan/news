package am.tatev.news.data.api

import am.tatev.news.data.dto.NewsResponse
import retrofit2.http.GET

interface NewsApiService {

    @GET("temp/json.php")
    suspend fun fetchNews(): NewsResponse

}
