package com.example.moviecatalogue.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.viewpager2.widget.ViewPager2
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


object MatcherUtil {
    fun isPosition(expectedPosition: Int): BoundedMatcher<View, ViewPager2> {
        return object : BoundedMatcher<View, ViewPager2>(ViewPager2::class.java) {
            public override fun matchesSafely(view: ViewPager2): Boolean {
                return view.currentItem == expectedPosition
            }

            override fun describeTo(description: Description) {
                description.appendText("Position $expectedPosition")
            }
        }
    }

    fun withTotalItem(total: Int): BoundedMatcher<View, RecyclerView> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            public override fun matchesSafely(view: RecyclerView): Boolean {
                return view.adapter?.itemCount == total
            }

            override fun describeTo(description: Description) {
                description.appendText("Match item with total $total")
            }
        }
    }

    fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("ImageView with drawable same as drawable with id $id")
        }

        override fun matchesSafely(view: View): Boolean {
            val context = view.context
            val expectedBitmap = context.getDrawable(id)?.toBitmap()

            return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
        }
    }

    fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position) ?: return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    fun withCompoundDrawableEnd(@DrawableRes id: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has compound drawable resource $id")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                val context = textView.context
                val expectedBitmap = context.getDrawable(id)?.toBitmap()

                return textView.compoundDrawables[2].toBitmap().sameAs(expectedBitmap)
            }
        }
    }
}