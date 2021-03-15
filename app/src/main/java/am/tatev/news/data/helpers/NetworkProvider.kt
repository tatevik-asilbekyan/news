package am.tatev.news.data.helpers

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

private const val BASE_URL = "https://www.helix.am/"
private const val NETWORK_TAG = "network"

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor { message ->
        Timber.tag(NETWORK_TAG).v(message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .build()

fun provideRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

fun provideGson(): Gson =
    Gson()