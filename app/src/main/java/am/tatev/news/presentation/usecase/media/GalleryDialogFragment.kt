package am.tatev.news.presentation.usecase.media

import am.tatev.news.R
import am.tatev.news.data.dto.BaseResource
import am.tatev.news.data.dto.Image
import am.tatev.news.data.dto.Video
import am.tatev.news.databinding.FragmentGalleryBinding
import am.tatev.news.helpers.navigateToImagePreview
import am.tatev.news.helpers.navigateToVideoPreview
import am.tatev.news.presentation.helpers.SpaceItemDecoration
import am.tatev.news.presentation.usecase.media.GalleryAdapter.Companion.ITEM_TYPE_IMAGE
import am.tatev.news.presentation.usecase.media.GalleryAdapter.Companion.ITEM_TYPE_VIDEO
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GalleryDialogFragment : BottomSheetDialogFragment() {

    private var galleryBinding: FragmentGalleryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGalleryBinding.inflate(inflater, container, false).also {
            galleryBinding = it
        }
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            val space = resources.getDimensionPixelSize(R.dimen.gallery_item_space)
            addItemDecoration(SpaceItemDecoration(0, space))

            val images = arguments?.getParcelableArrayList<Image>(EXTRA_IMAGES)
            val videos = arguments?.getParcelableArrayList<Video>(EXTRA_VIDEOS)
            val galleryDataSource = mutableListOf<BaseResource>().apply {
                images?.let {
                    addAll(it)
                }
                videos?.let {
                    addAll(it)
                }
            }
            if (galleryDataSource.isNotEmpty())
                adapter = GalleryAdapter(galleryDataSource) { item, type ->
                    when (type) {
                        ITEM_TYPE_IMAGE -> navigateToImagePreview(context, (item as Image).contentUrl)
                        ITEM_TYPE_VIDEO -> navigateToVideoPreview(context, (item as Video).youtubeId)
                    }
                }
        }
        return binding.root
    }

    override fun onDestroyView() {
        galleryBinding = null
        super.onDestroyView()
    }

    companion object {
        private const val SPAN_COUNT = 3
        private const val EXTRA_IMAGES = "extra_images"
        private const val EXTRA_VIDEOS = "extra_videos"

        fun newInstance(images: ArrayList<Image>? = null, videos: ArrayList<Video>? = null) =
            GalleryDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_IMAGES, images)
                    putParcelableArrayList(EXTRA_VIDEOS, videos)
                }
            }
    }
}