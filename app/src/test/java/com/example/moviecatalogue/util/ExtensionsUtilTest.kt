package com.example.moviecatalogue.util

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.R
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExtensionsUtilTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val context = mockk<Context>()

    @Before
    fun setUp() {
        every { context.getString(R.string.default_string_text) } answers { "-" }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testStringGetOrDefault() {
        val dummyText: String? = null

        assertEquals("-", dummyText.getOrDefault(context))
        assertEquals("Something", "Something".getOrDefault(context))
    }

    @Test
    fun testIntGetOrDefault() {
        val dummyText: String? = null

        assertEquals("-", dummyText.getOrDefault(context))
        assertEquals("Something", "Something".getOrDefault(context))
    }

    @Test
    fun testIntPrettyCount() {
        assertEquals("1", 1.prettyCount())
        assertEquals("1.0k", 1000.prettyCount())
        assertEquals("1.0M", 1005000.prettyCount())
        assertEquals("1.2B", 1230000000.prettyCount())
    }

    @Test
    fun testLiveDataGetOrAwaitValue() {
        val number = MutableLiveData<Int>()
        number.postValue(1)
        assertEquals(1, number.value)
    }
}