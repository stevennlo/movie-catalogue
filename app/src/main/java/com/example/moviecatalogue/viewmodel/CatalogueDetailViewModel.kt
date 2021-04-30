package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.repository.MediaRepository
import com.example.moviecatalogue.wrapper.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogueDetailViewModel @Inject constructor(private val mediaRepository: MediaRepository) :
    BaseViewModel() {
    private val _media = MutableLiveData<Status<MediaQuery.Media>>()
    val media: LiveData<Status<MediaQuery.Media>> get() = _media

    fun getCatalogueDetail(mediaId: Int) {
        launchViewModelScope {
            _media.postValue(mediaRepository.getMedia(mediaId))
        }
    }
}