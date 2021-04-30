package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.graphql.MediasQuery
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.repository.MediaRepository
import com.example.moviecatalogue.util.CalendarUtil
import com.example.moviecatalogue.wrapper.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val calendar: CalendarUtil,
) :
    BaseViewModel() {
    private var _medias = MutableLiveData<Status<List<MediasQuery.Medium>>>()
    val medias: LiveData<Status<List<MediasQuery.Medium>>> get() = _medias

    fun getCatalogue(format: MediaFormat) {
        launchViewModelScope {
            _medias.postValue(mediaRepository.getMedias(calendar.getPreviousYear(),
                calendar.getNextSeason(),
                format))
        }
    }
}