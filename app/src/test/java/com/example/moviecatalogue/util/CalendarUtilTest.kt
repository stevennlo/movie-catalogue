package com.example.moviecatalogue.util

import com.example.moviecatalogue.data.DummyData.getCalendar
import com.example.moviecatalogue.data.DummyData.getSeason
import com.example.moviecatalogue.data.DummyData.getYear
import com.example.moviecatalogue.repository.MediaRepository
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class CalendarUtilTest {
    @Before
    fun setUp() {
        mockkObject(MediaRepository)
        every { MediaRepository.getMediaSeason(getCalendar().get(Calendar.MONTH)) } answers { getSeason() }
    }

    @Test
    fun testGetPreviousYear() {
        val calendarUtil = CalendarUtil(getCalendar())
        assertEquals(getYear(), calendarUtil.getPreviousYear())
    }

    @Test
    fun testGetNextSeason() {
        val calendarUtil = CalendarUtil(getCalendar())
        assertEquals(getSeason(), calendarUtil.getNextSeason())
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}