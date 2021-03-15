package am.tatev.news.helpers

import am.tatev.news.data.dto.Article
import am.tatev.news.presentation.usecase.detail.ArticleActivity
import am.tatev.news.presentation.usecase.landing.MainActivity
import am.tatev.news.presentation.usecase.media.ResourcePreviewActivity
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

const val EXTRA_ARTICLE = "extra_article"
const val EXTRA_RESOURCE_TYPE = "extra_type"
const val EXTRA_RESOURCE_PATH = "extra_path"
const val RESOURCE_TYPE_IMAGE = 1
const val RESOURCE_TYPE_VIDEO = 2
const val RESOURCE_TYPE_UNSUPPORTED = 3

fun AppCompatActivity.showFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String? = null) {
    supportFragmentManager.commit {
        replace(containerViewId, fragment, tag)
    }
}

fun navigateToLandingAndFinish(activity: Activity) {
    activity.startActivity(Intent(activity, MainActivity::class.java))
    activity.finish()
}

fun navigateToArticle(context: Context, article: Article) {
    val intent = Intent(context, ArticleActivity::class.java).apply {
        putExtra(EXTRA_ARTICLE, article)
    }
    context.startActivity(intent)
}

fun navigateToImagePreview(context: Context, path: String) {
    val intent = Intent(context, ResourcePreviewActivity::class.java).apply {
        putExtra(EXTRA_RESOURCE_TYPE, RESOURCE_TYPE_IMAGE)
        putExtra(EXTRA_RESOURCE_PATH, path)
    }
    context.startActivity(intent)
}

fun navigateToVideoPreview(context: Context, path: String) {
    val intent = Intent(context, ResourcePreviewActivity::class.java).apply {
        putExtra(EXTRA_RESOURCE_TYPE, RESOURCE_TYPE_VIDEO)
        putExtra(EXTRA_RESOURCE_PATH, path)
    }
    context.startActivity(intent)
}
