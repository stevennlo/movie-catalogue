package com.example.moviecatalogue.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.R
import com.example.moviecatalogue.di.module.BaseUrlModule
import com.example.moviecatalogue.util.ActionUtil.selectTabAtPosition
import com.example.moviecatalogue.util.MatcherUtil.isPosition
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
@UninstallModules(BaseUrlModule::class)
class CatalogueFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    private var scenario: ActivityScenario<MainActivity>? = null

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario?.close()
    }

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