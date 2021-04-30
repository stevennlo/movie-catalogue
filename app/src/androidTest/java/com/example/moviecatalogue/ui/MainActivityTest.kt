package com.example.moviecatalogue.ui

import androidx.navigation.fragment.NavHostFragment
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.R
import com.example.moviecatalogue.di.module.BaseUrlModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
@UninstallModules(BaseUrlModule::class)
class MainActivityTest {
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
    fun testLaunchMainActivity() {
        (scenario as ActivityScenario<MainActivity>).onActivity {
            val navController =
                (it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            assertEquals(R.id.catalogueFragment, navController.currentDestination?.id)
            assertEquals(it.getString(R.string.app_name), navController.currentDestination?.label)
            assertEquals(0f, it.supportActionBar?.elevation)
        }
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }
}