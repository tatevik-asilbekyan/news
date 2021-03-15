package am.tatev.news.presentation.usecase.detail

import am.tatev.news.data.dto.Article
import am.tatev.news.databinding.ActivityArticleBinding
import am.tatev.news.helpers.EXTRA_ARTICLE
import am.tatev.news.helpers.showFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Article>(EXTRA_ARTICLE)?.let { article ->
            showFragment(binding.container.id, ArticleFragment.newInstance(article), TAG)
        }
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    companion object {
        private const val TAG = "article"
    }
}