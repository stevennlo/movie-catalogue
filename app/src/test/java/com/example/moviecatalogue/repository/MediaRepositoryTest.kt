package com.example.moviecatalogue.repository

import androidx.test.espresso.IdlingRegistry
import com.apollographql.apollo.ApolloClient
import com.example.moviecatalogue.data.DummyData.BASE_TEST_PORT
import com.example.moviecatalogue.data.DummyData.getFormat
import com.example.moviecatalogue.data.DummyData.getMediaBody
import com.example.moviecatalogue.data.DummyData.getMediaId
import com.example.moviecatalogue.data.DummyData.getMediasBody
import com.example.moviecatalogue.data.DummyData.getSeason
import com.example.moviecatalogue.data.DummyData.getYear
import com.example.moviecatalogue.graphql.type.MediaSeason.*
import com.example.moviecatalogue.util.EspressoUtil
import com.example.moviecatalogue.wrapper.Status
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

class MediaRepositoryTest {
    private lateinit var mediaRepository: MediaRepository
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start(BASE_TEST_PORT)
        val apiService = ApolloClient.builder()
            .serverUrl(mockWebServer.url("/"))
            .build()
        mediaRepository = MediaRepositoryImpl(apiService)
        IdlingRegistry.getInstance().register(EspressoUtil.idlingResource)
    }

    @After
    fun tearDown() {
        unmockkAll()
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoUtil.idlingResource)
    }

    @Test
    fun testGetMedias() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(getMediasBody()))
            val resultSuccess = mediaRepository.getMedias(getYear(), getSeason(), getFormat())
            assertEquals(Status.StatusType.SUCCESS, resultSuccess.status)
            assertNotNull(resultSuccess.data)

            mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST))
            val resultFailed = mediaRepository.getMedias(getYear(), getSeason(), getFormat())
            assertEquals(Status.StatusType.FAILED, resultFailed.status)
            assertNull(resultFailed.data)
        }

    }

    @Test
    fun testGetMedia() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(getMediaBody()))
            val resultSuccess = mediaRepository.getMedia(getMediaId())
            assertEquals(Status.StatusType.SUCCESS, resultSuccess.status)
            assertNotNull(resultSuccess.data)

            mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST))
            val resultFailed = mediaRepository.getMedia(getMediaId())
            assertEquals(Status.StatusType.FAILED, resultFailed.status)
            assertNull(resultFailed.data)
        }
    }

    @Test
    fun testGetMediaSeason() {
        val expected = listOf(null, WINTER, WINTER, SPRING, SPRING,
            SPRING, SUMMER, SUMMER, SUMMER, FALL, FALL, FALL, WINTER)
        for (month in 1..12) {
            assertEquals("Month $month", expected[month], MediaRepository.getMediaSeason(month))
        }
    }
}