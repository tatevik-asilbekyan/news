package am.tatev.news.presentation.usecase.news

import am.tatev.news.R
import am.tatev.news.data.dto.Article
import am.tatev.news.databinding.ListItemArticleBinding
import am.tatev.news.presentation.helpers.asyncLoadImage
import am.tatev.news.presentation.helpers.toHumanReadableDate
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ArticlesAdapter(
    private val callback: (article: Article, position: Int) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private val dataSource = mutableListOf<Article>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val binding = ListItemArticleBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = dataSource[position]
        holder.binding.title.text = article.title
        holder.binding.title.setTypeface(null, if (article.seen)
            Typeface.NORMAL
        else
            Typeface.BOLD
        )
        holder.binding.category.text = article.category
        holder.binding.date.text = article.date.toHumanReadableDate()
        holder.binding.cover.asyncLoadImage(article.coverPhotoUrl,
            holder.binding.cover.resources.getDimensionPixelOffset(R.dimen.card_image_rounding_radius))
        holder.binding.root.setCardBackgroundColor(
            ContextCompat.getColor(holder.binding.root.context, if (article.seen)
                R.color.gray_50
            else
                R.color.gray_100
            )
        )
        holder.binding.root.setOnClickListener {
            callback(article, position)
        }
    }

    override fun getItemCount(): Int =
        dataSource.size

    fun addNews(dataSource: List<Article>) {
        this.dataSource.clear()
        this.dataSource.addAll(dataSource)
        notifyDataSetChanged()
    }

    class ArticleViewHolder(val binding: ListItemArticleBinding): RecyclerView.ViewHolder(binding.root)

}