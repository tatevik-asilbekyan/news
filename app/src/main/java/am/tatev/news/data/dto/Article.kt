package am.tatev.news.data.dto

import am.tatev.news.data.helpers.ImageConverter
import am.tatev.news.data.helpers.VideoConverter
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Article(
    @PrimaryKey
    val date: Long,
    val category: String,
    val title: String,
    val body: String,
    val shareUrl: String,
    val coverPhotoUrl: String,
    @TypeConverters(ImageConverter::class)
    val gallery: List<Image>?,
    @TypeConverters(VideoConverter::class)
    val video: List<Video>?,
    var seen: Boolean): Parcelable

sealed class BaseResource {
    val title: String? = null
}

@Parcelize
data class Image(
    val contentUrl: String,
    val thumbnailUrl: String
) : BaseResource(), Parcelable

@Parcelize
data class Video(
    val youtubeId: String,
    val thumbnailUrl: String
) : BaseResource(), Parcelable



