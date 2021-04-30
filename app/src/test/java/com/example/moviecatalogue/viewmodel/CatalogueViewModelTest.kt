package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.DummyData.getCalendar
import com.example.moviecatalogue.data.DummyData.getMedias
import com.example.moviecatalogue.graphql.MediasQuery
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.graphql.type.MediaSeason
import com.example.moviecatalogue.repository.MediaRepositoryImpl
import com.example.moviecatalogue.util.CalendarUtil
import com.example.moviecatalogue.wrapper.Status
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class CatalogueViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<MediaRepositoryImpl>()
    private val viewModel = CatalogueViewModel(repository, CalendarUtil(getCalendar()))
    private val dummyYear = 2020
    private val dummySeason = MediaSeason.SPRING
    private val dummyFormat = MediaFormat.TV
    private val dummyStatus = Status.success(getMedias())
    private val observer = spyk<Observer<Status<List<MediasQuery.Medium>>>>()

    @Before
    fun setUp() {
        mockkStatic(Calendar::class)
        every { Calendar.getInstance() } answers { getCalendar() }
        coEvery {
            repository.getMedias(dummyYear,
                dummySeason,
                dummyFormat)
        } coAnswers { dummyStatus }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetCatalogue() {
        viewModel.medias.observeForever(observer)
        viewModel.getCatalogue(dummyFormat)
        verify {
            observer.onChanged(dummyStatus)
        }
    }
}