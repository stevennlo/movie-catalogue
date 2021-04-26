package com.example.moviecatalogue.ui

import androidx.navigation.fragment.NavHostFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.R
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testLaunchMainActivity() {
        scenarioRule.scenario.onActivity {
            val navController =
                (it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            assertEquals(R.id.catalogueFragment, navController.currentDestination?.id)
            assertEquals(it.getString(R.string.app_name), navController.currentDestination?.label)
            assertEquals(0f, it.supportActionBar?.elevation)
        }
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }
}