package com.example.moviecatalogue.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.R
import com.example.moviecatalogue.util.ActionUtil.selectTabAtPosition
import com.example.moviecatalogue.util.MatcherUtil.isPosition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class CatalogueFragmentTest {
    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testTvShowTab() {
        onView(withId(R.id.catalogue_view_vp2)).check(matches(isPosition(0)))
        onView(withId(R.id.catalogue_tabs_tl)).perform(selectTabAtPosition(0))
        onView(withId(R.id.catalogue_view_vp2)).check(matches(isPosition(0)))
    }

    @Test
    fun testMovieTab() {
        onView(withId(R.id.catalogue_tabs_tl)).perform(selectTabAtPosition(1))
        onView(withId(R.id.catalogue_view_vp2)).check(matches(isPosition(1)))
    }
}