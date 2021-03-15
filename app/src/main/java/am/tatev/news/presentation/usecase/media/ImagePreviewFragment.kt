package am.tatev.news.presentation.usecase.media

import am.tatev.news.R
import am.tatev.news.databinding.FragmentImagePreviewBinding
import am.tatev.news.presentation.helpers.asyncLoadImage
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class ImagePreviewFragment : Fragment(R.layout.fragment_image_preview) {

    private var imagePreviewBinding: FragmentImagePreviewBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentImagePreviewBinding.bind(view).also {
            imagePreviewBinding = it
        }

        val path = arguments?.getString(EXTRA_PATH) ?: ""
        binding.preview.asyncLoadImage(path)
    }

    override fun onDestroyView() {
        imagePreviewBinding = null
        super.onDestroyView()
    }

    companion object {
        private const val EXTRA_PATH = "path"

        fun newInstance(path: String?) =
            ImagePreviewFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_PATH, path)
                }
            }
    }
}