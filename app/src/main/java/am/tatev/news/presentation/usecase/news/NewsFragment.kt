package am.tatev.news.presentation.usecase.news

import am.tatev.news.R
import am.tatev.news.data.dto.Article
import am.tatev.news.databinding.FragmentNewsBinding
import am.tatev.news.helpers.navigateToArticle
import am.tatev.news.presentation.helpers.SpaceItemDecoration
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private var newsBinding: FragmentNewsBinding? = null
    private val newsViewModel: NewsViewModel by viewModel()
    private var newsAdapter: ArticlesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsBinding.bind(view).also {
            newsBinding = it
        }

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            val space = resources.getDimensionPixelSize(R.dimen.card_space)
            addItemDecoration(SpaceItemDecoration(space, space))
            newsAdapter = ArticlesAdapter { article, position ->
                navigateToArticle(context, article)
                checkArticleSeen(article, position)
            }
            adapter = newsAdapter
        }
        newsViewModel.isLoading.observe(viewLifecycleOwner, {
            binding.progressBar.isVisible = it
        })
        newsViewModel.news.observe(viewLifecycleOwner, {
            newsAdapter?.addNews(it)
        })
    }

    override fun onDestroyView() {
        newsBinding = null
        super.onDestroyView()
    }

    private fun checkArticleSeen(article: Article, position: Int) {
        if (article.seen.not()) {
            article.seen = true
            newsViewModel.onArticleSeen(article)
            newsAdapter?.notifyItemChanged(position)
        }
    }
}