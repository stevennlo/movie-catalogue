package com.example.moviecatalogue.util

import com.example.moviecatalogue.R
import com.example.moviecatalogue.util.ImageUtil.getEmoResourceId
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageUtilTest {
    @Test
    fun testGetEmoResourceId() {
        assertEquals(R.drawable.ic_neutral, getEmoResourceId(null))
        assertEquals(R.drawable.ic_sad, getEmoResourceId(12))
        assertEquals(R.drawable.ic_neutral, getEmoResourceId(34))
        assertEquals(R.drawable.ic_smile, getEmoResourceId(80))
        assertEquals(R.drawable.ic_smile, getEmoResourceId(101))
    }
}