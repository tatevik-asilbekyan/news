package am.tatev.news.data.di

import am.tatev.news.data.helpers.provideGson
import am.tatev.news.data.helpers.provideHttpLoggingInterceptor
import am.tatev.news.data.helpers.provideOkHttpClient
import am.tatev.news.data.helpers.provideRetrofit
import org.koin.dsl.module

val NetworkModule = module {

    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(loggingInterceptor = get()) }
    single { provideRetrofit(client = get()) }
    single { provideGson() }

}