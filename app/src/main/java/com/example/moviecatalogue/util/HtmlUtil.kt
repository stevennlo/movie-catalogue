package com.example.moviecatalogue.util

import android.text.SpannableString
import android.text.Spanned
import androidx.core.text.HtmlCompat

object HtmlUtil {
    fun fromHtml(html: String?): Spanned {
        return when (html) {
            null -> SpannableString("")
            else -> HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}