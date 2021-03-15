package am.tatev.news.data.helpers

import am.tatev.news.data.dto.Image
import am.tatev.news.data.dto.Video
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ImageConverter {

    @TypeConverter
    fun fromImages(images: List<Image>?): String? {
        val type: Type = object : TypeToken<List<Image>?>() {}.type
        return Gson().toJson(images, type)
    }

    @TypeConverter
    fun toImages(imagesString: String?): List<Image>? {
        val type: Type = object : TypeToken<List<Image>?>() {}.type
        return Gson().fromJson(imagesString, type)
    }
}

class VideoConverter {

    @TypeConverter
    fun fromVideos(videos: List<Video>?): String? {
        val type: Type = object : TypeToken<List<Video>?>() {}.type
        return Gson().toJson(videos, type)
    }

    @TypeConverter
    fun toVideos(videosString: String?): List<Video>? {
        val type: Type = object : TypeToken<List<Video>?>() {}.type
        return Gson().fromJson(videosString, type)
    }
}