package com.example.retrofittest.paging.util

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl", "error")
    fun setImageUrl(imageView: ImageView, url: String, errorDrawable: Drawable) {
        Glide.with(imageView.context)
            .load(url)
            .error(errorDrawable)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("fromHtml")
    fun setFromHtml(textView: TextView, source: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT)
        } else {
            textView.text = Html.fromHtml(source)
        }
    }

    @JvmStatic
    @BindingAdapter("directors")
    fun setDirectors(textView: TextView, source: String) {
        if(source.endsWith("|")) {
            textView.text = source.subSequence(0, source.length-1)
        } else {
            textView.text = source
        }
    }
}