package am.tatev.news.data.db

import am.tatev.news.data.dto.Article
import am.tatev.news.data.helpers.ImageConverter
import am.tatev.news.data.helpers.VideoConverter
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val DB_VERSION = 1

@Database(entities = [Article::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(ImageConverter::class, VideoConverter::class)
abstract class AppDb: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}