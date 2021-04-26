package com.example.moviecatalogue.util

import com.example.moviecatalogue.graphql.type.MediaSeason
import com.example.moviecatalogue.repository.MediaRepository
import java.util.*

class CalendarUtil(private val calendar: Calendar = Calendar.getInstance()) {
    fun getPreviousYear(): Int {
        val currentYear = calendar.get(Calendar.YEAR)
        return currentYear - 1
    }

    fun getCurrentSeason(): MediaSeason {
        val month = calendar.get(Calendar.MONTH) + 1
        return MediaRepository.getMediaSeason(month)
    }
}