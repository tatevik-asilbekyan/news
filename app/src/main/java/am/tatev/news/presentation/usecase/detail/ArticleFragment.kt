package am.tatev.news.presentation.usecase.detail

import am.tatev.news.R
import am.tatev.news.data.dto.Article
import am.tatev.news.databinding.FragmentArticleBinding
import am.tatev.news.helpers.EXTRA_ARTICLE
import am.tatev.news.presentation.helpers.asyncLoadImage
import am.tatev.news.presentation.helpers.toHumanReadableDate
import am.tatev.news.presentation.usecase.media.GalleryDialogFragment
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var articleBinding: FragmentArticleBinding? = null
    private var article: Article? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArticleBinding.bind(view).also {
            articleBinding = it
        }

        article = arguments?.getParcelable(EXTRA_ARTICLE)

        binding.title.text = getString(R.string.text_separator, article?.category, article?.title)
        binding.date.text = article?.date?.toHumanReadableDate()
        article?.coverPhotoUrl?.let {
            binding.cover.asyncLoadImage(it)
        }
        article?.body?.let {
            setupReadMore(it)
        }

        binding.imageGallery.isVisible = article?.gallery.isNullOrEmpty().not()
        binding.videoGallery.isVisible = article?.video.isNullOrEmpty().not()

        binding.imageGallery.setOnClickListener {
            GalleryDialogFragment.newInstance(images = ArrayList(article?.gallery))
                .showNow(childFragmentManager, TAG)
        }
        binding.videoGallery.setOnClickListener {
            GalleryDialogFragment.newInstance(videos = ArrayList(article?.video))
                .showNow(childFragmentManager, TAG)
        }
    }

    override fun onDestroyView() {
        articleBinding = null
        super.onDestroyView()
    }

    private fun setupReadMore(text: String) {
        articleBinding?.body?.viewTreeObserver?.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                articleBinding?.body?.viewTreeObserver?.removeOnPreDrawListener(this)
                if (articleBinding?.body?.lineCount!! > MAX_LINES) {

                    articleBinding?.body?.maxLines = MAX_LINES
                    articleBinding?.body?.ellipsize = TextUtils.TruncateAt.END
                    articleBinding?.body?.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)

                    articleBinding?.readMore?.isVisible = true
                    articleBinding?.readMore?.setOnClickListener {
                        articleBinding?.readMore?.isVisible = false
                        articleBinding?.readLess?.isVisible = true
                        articleBinding?.body?.maxLines = Int.MAX_VALUE
                        articleBinding?.body?.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
                    }
                    articleBinding?.readLess?.setOnClickListener {
                        articleBinding?.readMore?.isVisible = true
                        articleBinding?.readLess?.isVisible = false
                        articleBinding?.body?.maxLines = MAX_LINES
                        articleBinding?.body?.ellipsize = TextUtils.TruncateAt.END
                        articleBinding?.body?.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
                    }
                }
                return true
            }
        })
        articleBinding?.body?.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    companion object {
        private const val TAG = "gallery"
        private const val MAX_LINES = 10

        fun newInstance(article: Article) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_ARTICLE, article)
                }
            }
    }
}