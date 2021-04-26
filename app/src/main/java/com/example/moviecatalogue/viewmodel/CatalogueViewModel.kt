package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalogue.graphql.MediasQuery
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.repository.MediaRepository
import com.example.moviecatalogue.service.Status
import com.example.moviecatalogue.util.CalendarUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogueViewModel(
    private val mediaRepository: MediaRepository,
    private val calendar: CalendarUtil = CalendarUtil(),
) :
    ViewModel() {
    private var _medias = MutableLiveData<Status<List<MediasQuery.Medium>>>()
    val medias: LiveData<Status<List<MediasQuery.Medium>>> get() = _medias

    fun getCatalogue(format: MediaFormat) {
        viewModelScope.launch(Dispatchers.IO) {
            _medias.postValue(mediaRepository.getMedias(calendar.getPreviousYear(),
                calendar.getCurrentSeason(),
                format))
        }
    }
}