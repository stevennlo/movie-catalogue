package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.data.DummyData.getMedia
import com.example.moviecatalogue.repository.MediaRepository
import com.example.moviecatalogue.service.Status
import com.example.moviecatalogue.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.*

open class CatalogueDetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<MediaRepository>()
    private val viewModel = CatalogueDetailViewModel(repository)
    private val dummyMediaId = 110733
    private val dummyStatus = Status.success(getMedia())

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
        viewModel.getCatalogueDetail(dummyMediaId)
        Assert.assertEquals(dummyStatus, viewModel.media.getOrAwaitValue())
    }
}