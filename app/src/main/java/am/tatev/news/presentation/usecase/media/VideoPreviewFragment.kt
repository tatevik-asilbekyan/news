package am.tatev.news.presentation.usecase.media

import am.tatev.news.R
import am.tatev.news.databinding.FragmentVideoPreviewBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback

class VideoPreviewFragment : Fragment(R.layout.fragment_video_preview) {

    private var videoPreviewBinding: FragmentVideoPreviewBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentVideoPreviewBinding.bind(view).also {
            videoPreviewBinding = it
        }

        val path = arguments?.getString(EXTRA_PATH) ?: ""
        binding.player.getYouTubePlayerWhenReady(object: YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(path, 0f)
            }
        })
    }

    override fun onDestroyView() {
        videoPreviewBinding = null
        super.onDestroyView()
    }

    companion object {
        private const val EXTRA_PATH = "path"

        fun newInstance(path: String?) =
            VideoPreviewFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_PATH, path)
                }
            }
    }
}