package com.example.moviecatalogue.util

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.example.moviecatalogue.R
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true,
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun String?.getOrDefault(context: Context) =
    this ?: context.getString(R.string.default_string_text)

fun Int?.getOrDefault(context: Context, prettify: Boolean = false): String {
    return if (prettify) {
        this?.prettyCount() ?: context.getString(R.string.default_string_text)
    } else this?.toString() ?: context.getString(R.string.default_string_text)
}

fun Int.prettyCount(): String {
    val suffix = charArrayOf('\u0000', 'k', 'M', 'B')
    val numValue = toLong()
    val value = floor(log10(numValue.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(
            numValue / 10.0.pow((base * 3).toDouble())
        ) + suffix[base]
    } else {
        DecimalFormat("#,##0").format(numValue)
    }
}