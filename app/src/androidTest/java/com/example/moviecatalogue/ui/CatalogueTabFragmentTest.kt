package com.example.moviecatalogue.ui

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.CatalogueAdapter
import com.example.moviecatalogue.data.DummyData.BASE_TEST_PORT
import com.example.moviecatalogue.data.DummyData.getEmptyMediasBody
import com.example.moviecatalogue.data.DummyData.getMedias
import com.example.moviecatalogue.data.DummyData.getMediasBody
import com.example.moviecatalogue.di.module.BaseUrlModule
import com.example.moviecatalogue.util.ActionUtil.withCustomConstraints
import com.example.moviecatalogue.util.EspressoUtil
import com.example.moviecatalogue.util.MatcherUtil.atPosition
import com.example.moviecatalogue.util.MatcherUtil.withDrawable
import com.example.moviecatalogue.util.MatcherUtil.withTotalItem
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
@UninstallModules(BaseUrlModule::class)
class CatalogueTabFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    private val mockWebServer = MockWebServer()
    private var scenario: ActivityScenario<MainActivity>? = null

    @Before
    fun setUp() {
        mockWebServer.start(BASE_TEST_PORT)
        IdlingRegistry.getInstance().register(EspressoUtil.idlingResource)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        scenario?.close()
        IdlingRegistry.getInstance().unregister(EspressoUtil.idlingResource)
    }

    @Test
    fun testCatalogueTab_whenSuccessResponse() {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(getMediasBody()))
        scenario = ActivityScenario.launch(MainActivity::class.java)
        var navController: NavController? = null
        scenario?.onActivity {
            navController =
                (it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        }
        assertEquals(R.id.catalogueFragment, navController?.currentDestination?.id)
        onView(withId(R.id.catalogue_tab_list_rv)).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(R.id.catalogue_tab_list_rv),
            isCompletelyDisplayed())).check(matches(withTotalItem(getMedias().size)))
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(getMediasBody()))
        onView(withId(R.id.catalogue_tab_refresh_srl)).perform(withCustomConstraints(swipeDown(),
            isDisplayingAtLeast(85)))
        onView(allOf(withId(R.id.catalogue_tab_list_rv),
            isCompletelyDisplayed())).check(matches(withTotalItem(getMedias().size)))
    }

    @Test
    fun testCatalogueTab_whenResponseEmpty() {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(getEmptyMediasBody()))
        scenario = ActivityScenario.launch(MainActivity::class.java)
        var context: Context? = null
        var navController: NavController? = null
        scenario?.onActivity {
            navController =
                (it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            context = it.applicationContext
        }
        assertEquals(R.id.catalogueFragment, navController?.currentDestination?.id)
        onView(withId(R.id.message_root_ll)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.main_message_image_iv)).check(matches(withDrawable(R.drawable.ic_not_found)))
        onView(withId(R.id.main_message_description_tv)).check(matches(withText(context?.getString(R.string.nothing_here))))
    }

    @Test
    fun testCatalogueTab_whenResponseFailed() {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST))
        scenario = ActivityScenario.launch(MainActivity::class.java)
        var navController: NavController? = null
        var context: Context? = null
        scenario?.onActivity {
            navController =
                (it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            context = it.applicationContext
        }
        assertEquals(R.id.catalogueFragment, navController?.currentDestination?.id)
        onView(withId(R.id.message_root_ll)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.main_message_image_iv)).check(matches(withDrawable(R.drawable.ic_something_wrong)))
        onView(withId(R.id.main_message_description_tv)).check(matches(withText(context?.getString(R.string.unknown_error_message))))
    }

    @Test
    fun testCatalogueTab_whenNavigating() {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(getMediasBody()))
        scenario = ActivityScenario.launch(MainActivity::class.java)
        var navController: NavController? = null
        scenario?.onActivity {
            navController =
                (it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        }
        assertEquals(R.id.catalogueFragment, navController?.currentDestination?.id)
        onView(allOf(withId(R.id.catalogue_tab_list_rv),
            isCompletelyDisplayed())).check(matches(withTotalItem(getMedias().size)))
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST))
        onView(withId(R.id.catalogue_tab_list_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CatalogueAdapter.ViewHolder>(0,
                click()))
        assertEquals(R.id.catalogueDetailFragment, navController?.currentDestination?.id)
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        assertEquals(R.id.catalogueFragment, navController?.currentDestination?.id)
        onView(withId(R.id.catalogue_tab_list_rv)).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(R.id.catalogue_tab_list_rv),
            isCompletelyDisplayed())).check(matches(withTotalItem(getMedias().size)))
    }

    @Test
    fun testCatalogueTabContent() {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(getMediasBody()))
        scenario = ActivityScenario.launch(MainActivity::class.java)
        var navController: NavController? = null
        scenario?.onActivity {
            navController =
                (it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        }
        assertEquals(R.id.catalogueFragment, navController?.currentDestination?.id)
        onView(allOf(withId(R.id.catalogue_tab_list_rv),
            isCompletelyDisplayed())).check(matches(withTotalItem(getMedias().size)))
        val lastPosition = getMedias().size - 1
        onView(withId(R.id.catalogue_tab_list_rv))
            .perform(RecyclerViewActions.scrollToPosition<CatalogueAdapter.ViewHolder>(lastPosition))
        onView(withId(R.id.catalogue_tab_list_rv))
            .check(matches(atPosition(lastPosition,
                hasDescendant(withText(getMedias()[lastPosition].title?.romaji)))))
        onView(withId(R.id.catalogue_tab_list_rv))
            .check(matches(atPosition(lastPosition,
                hasDescendant(not(withDrawable(R.drawable.ic_default_movie))))))
    }
}