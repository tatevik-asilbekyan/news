package am.tatev.news.data.di

import am.tatev.news.data.db.AppDb
import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

private const val DB_NAME = "news.demo"

val DbModule = module {

    single {
        val context: Context = get()
        return@single Room.databaseBuilder(context, AppDb::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    single {
        val db: AppDb = get()
        db.newsDao()
    }
}