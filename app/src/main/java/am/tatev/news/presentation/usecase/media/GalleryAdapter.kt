package am.tatev.news.presentation.usecase.media

import am.tatev.news.data.dto.BaseResource
import am.tatev.news.data.dto.Image
import am.tatev.news.data.dto.Video
import am.tatev.news.databinding.ListItemGalleryBinding
import am.tatev.news.presentation.helpers.asyncLoadImage
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GalleryAdapter(
    private val dataSource: List<BaseResource>,
    private val callback: (item: BaseResource, type: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemGalleryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return when(viewType) {
            ITEM_TYPE_IMAGE -> ImageViewHolder(binding)
            ITEM_TYPE_VIDEO -> VideoViewHolder(binding)
            else -> throw RuntimeException("Type unsupported")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ImageViewHolder -> bindImageViewHolder(holder.binding, position)
            is VideoViewHolder -> bindVideoViewHolder(holder.binding, position)
        }
    }

    override fun getItemCount(): Int =
        dataSource.size

    override fun getItemViewType(position: Int): Int =
        if (dataSource[position] is Image)
            ITEM_TYPE_IMAGE
        else
            ITEM_TYPE_VIDEO

    private fun bindImageViewHolder(binding: ListItemGalleryBinding, position: Int) {
        val item = dataSource[position]
        binding.cover.asyncLoadImage((item as Image).thumbnailUrl)
        binding.root.setOnClickListener {
            callback(item, ITEM_TYPE_IMAGE)
        }
    }

    private fun bindVideoViewHolder(binding: ListItemGalleryBinding, position: Int) {
        val item = dataSource[position]
        binding.cover.asyncLoadImage((item as Video).thumbnailUrl)
        binding.root.setOnClickListener {
            callback(item, ITEM_TYPE_VIDEO)
        }
    }

    private class ImageViewHolder(val binding: ListItemGalleryBinding): RecyclerView.ViewHolder(binding.root)
    private class VideoViewHolder(val binding: ListItemGalleryBinding): RecyclerView.ViewHolder(binding.root)

    companion object {
        const val ITEM_TYPE_IMAGE = 1
        const val ITEM_TYPE_VIDEO = 2
    }
}