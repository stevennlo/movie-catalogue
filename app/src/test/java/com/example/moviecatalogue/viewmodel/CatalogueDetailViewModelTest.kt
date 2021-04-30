package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.DummyData.getMedia
import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.repository.MediaRepositoryImpl
import com.example.moviecatalogue.wrapper.Status
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

open class CatalogueDetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<MediaRepositoryImpl>()
    private val viewModel = CatalogueDetailViewModel(repository)
    private val dummyMediaId = 110733
    private val dummyStatus = Status.success(getMedia())
    private val observer = spyk<Observer<Status<MediaQuery.Media>>>()

    @Before
    fun setUp() {
        coEvery {
            repository.getMedia(dummyMediaId)
        } coAnswers { dummyStatus }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetCatalogueDetail() {
        viewModel.media.observeForever(observer)
        viewModel.getCatalogueDetail(dummyMediaId)
        verify {
            observer.onChanged(dummyStatus)
        }
    }
}