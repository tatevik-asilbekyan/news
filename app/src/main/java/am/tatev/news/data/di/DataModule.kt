package am.tatev.news.data.di

import am.tatev.news.data.api.NewsApiService
import am.tatev.news.data.repository.NewsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val DataModule = module {

    single {
        val retrofit: Retrofit = get()
        return@single retrofit.create(NewsApiService::class.java)
    }

    single { NewsRepository(context = get(), remoteService = get(), localService = get()) }

}