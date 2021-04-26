package com.example.moviecatalogue.ui

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.CharacterAdapter
import com.example.moviecatalogue.data.DummyData.getMedia
import com.example.moviecatalogue.data.DummyData.getMediaBody
import com.example.moviecatalogue.data.DummyData.getMediaId
import com.example.moviecatalogue.util.*
import com.example.moviecatalogue.util.MatcherUtil.atPosition
import com.example.moviecatalogue.util.MatcherUtil.withCompoundDrawableEnd
import com.example.moviecatalogue.util.MatcherUtil.withDrawable
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_OK

@RunWith(AndroidJUnit4ClassRunner::class)
class CatalogueDetailFragmentTest {
    private val mockWebServer = MockWebServer()
    private var scenario: ActivityScenario<MainActivity>? = null

    @Before
    fun setUp() {
        mockWebServer.start(BASE_TEST_PORT)
        BASE_ANILIST_URL = mockWebServer.url("/").toString()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        scenario?.close()
    }

    @Test
    fun testCatalogueDetail() {
        mockWebServer.enqueue(MockResponse().setResponseCode(HTTP_BAD_REQUEST))
        scenario = ActivityScenario.launch(MainActivity::class.java)
        var navController: NavController? = null
        var context: Context? = null
        scenario?.onActivity {
            navController =
                (it.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            navController?.navigate(CatalogueFragmentDirections.actionCatalogueFragmentToCatalogueDetailFragment(
                getMediaId()))
            context = it.applicationContext
        }
        Assert.assertEquals(R.id.catalogueDetailFragment, navController?.currentDestination?.id)
        mockWebServer.enqueue(MockResponse().setResponseCode(HTTP_BAD_REQUEST))
        Thread.sleep(2000)
        onView(withId(R.id.message_root_ll)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.main_message_image_iv))
            .check(matches(withDrawable(R.drawable.ic_something_wrong)))
        onView(withId(R.id.main_message_description_tv))
            .check(matches(withText(context?.getString(R.string.unknown_error_message))))
        mockWebServer.enqueue(MockResponse().setResponseCode(HTTP_OK)
            .setBody(getMediaBody()))
        onView(withId(R.id.catalogue_detail_refresh_srl)).perform(ActionUtil.withCustomConstraints(
            swipeDown(), isDisplayingAtLeast(85)))
        Thread.sleep(2000)
        onView(withId(R.id.message_root_ll)).check(matches(not(isCompletelyDisplayed())))
        onView(withId(R.id.catalogue_detail_banner_siv))
            .check(matches(not(withDrawable(R.drawable.ic_default_movie))))
        onView(withId(R.id.catalogue_detail_image_siv))
            .check(matches(not(withDrawable(R.drawable.ic_default_movie))))
        onView(withId(R.id.catalogue_detail_title_tv)).check(matches(withText(getMedia().title?.romaji)))
        onView(withId(R.id.catalogue_detail_score_tv)).check(matches(withText(String.format(context?.getString(
            R.string.score_value) as String,
            getMedia().averageScore.getOrDefault(context as Context)))))
        onView(withId(R.id.catalogue_detail_score_tv)).check(matches(withCompoundDrawableEnd(
            ImageUtil.getEmoResourceId(getMedia().averageScore))))
        onView(withId(R.id.catalogue_detail_favorite_tv)).check(matches(withText(getMedia().favourites.getOrDefault(
            context as Context))))
        onView(withId(R.id.catalogue_detail_episode_tv)).check(matches(withText(getMedia().episodes.getOrDefault(
            context as Context))))
        onView(withId(R.id.catalogue_detail_duration_tv)).check(matches(withText(getMedia().duration.getOrDefault(
            context as Context))))
        onView(withId(R.id.main_message_description_tv)).check(matches(not(withText(context?.getString(
            R.string.default_string_text)))))
        onView(withId(R.id.catalogue_detail_root_cl)).perform(swipeUp())
        getMedia().genres?.filterNotNull()?.forEach {
            onView(withText(it)).check(matches(isDisplayed()))
        }
        onView(withId(R.id.catalogue_detail_character_list_rv)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.catalogue_detail_character_list_rv)).check(matches(MatcherUtil.withTotalItem(
            getMedia().characters?.edges?.size
                ?: 0)))
        getMedia().characters?.edges?.let {
            val lastPosition = it.size - 1
            onView(withId(R.id.catalogue_detail_character_list_rv))
                .perform(RecyclerViewActions.scrollToPosition<CharacterAdapter.ViewHolder>(
                    lastPosition))
            onView(withId(R.id.catalogue_detail_character_list_rv))
                .check(matches(atPosition(lastPosition,
                    hasDescendant(withText(it[lastPosition]?.node?.name?.full)))))
            onView(withId(R.id.catalogue_detail_character_list_rv))
                .check(matches(atPosition(lastPosition,
                    hasDescendant(withText(it[lastPosition]?.role.toString().toLowerCase()
                        .capitalize())))))
            onView(withId(R.id.catalogue_detail_character_list_rv))
                .check(matches(atPosition(lastPosition,
                    hasDescendant(withText(it[lastPosition]?.voiceActors?.first()?.name?.full)))))
            onView(withId(R.id.catalogue_detail_character_list_rv))
                .check(matches(atPosition(lastPosition,
                    hasDescendant(withText(it[lastPosition]?.voiceActors?.first()?.languageV2)))))
            onView(withId(R.id.catalogue_detail_character_list_rv))
                .check(matches(atPosition(lastPosition,
                    hasDescendant(not(withDrawable(R.drawable.ic_default_person))))))
        }
    }
}