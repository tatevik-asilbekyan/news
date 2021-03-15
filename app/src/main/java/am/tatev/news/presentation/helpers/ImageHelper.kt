package am.tatev.news.presentation.helpers

import am.tatev.news.R
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.asyncLoadImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .into(this)
}

fun ImageView.asyncLoadImage(url: String, roundingRadius: Int) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(roundingRadius)))
        .into(this)
}