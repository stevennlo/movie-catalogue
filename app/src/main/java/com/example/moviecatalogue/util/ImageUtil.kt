package com.example.moviecatalogue.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R

object ImageUtil {
    fun getDrawable(context: Context, id: Int): Drawable? {
        return ResourcesCompat.getDrawable(context.resources, id, null)
    }

    fun loadImage(
        imageUrl: String?,
        @DrawableRes defaultResourceId: Int,
        imageView: ImageView,
    ) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .error(defaultResourceId)
            .fallback(defaultResourceId)
            .centerCrop()
            .into(imageView)
    }

    fun getDefaultItemDecoration(context: Context): DividerItemDecoration {
        return DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
            setDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.drawable_space_item_decoration,
                    null
                )!!
            )
        }
    }

    fun getEmoResourceId(score: Int?): Int {
        val emojiResources = listOf(R.drawable.ic_sad, R.drawable.ic_neutral, R.drawable.ic_smile)
        return when (score) {
            null -> R.drawable.ic_neutral
            else -> {
                val finalScore = if (score > 100) 100 else score
                emojiResources[finalScore / 34]
            }
        }
    }
}