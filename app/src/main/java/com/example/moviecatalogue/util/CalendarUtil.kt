package com.example.moviecatalogue.util

import com.example.moviecatalogue.graphql.type.MediaSeason
import com.example.moviecatalogue.repository.MediaRepository
import java.util.*
import javax.inject.Inject

class CalendarUtil @Inject constructor(private val calendar: Calendar) {
    fun getPreviousYear(): Int {
        val currentYear = calendar.get(Calendar.YEAR)
        return currentYear - 1
    }

    fun getNextSeason(): MediaSeason {
        val month = calendar.get(Calendar.MONTH) + 4
        return MediaRepository.getMediaSeason(month)
    }
}