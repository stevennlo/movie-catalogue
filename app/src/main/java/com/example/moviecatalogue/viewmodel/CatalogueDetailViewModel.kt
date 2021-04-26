package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.repository.MediaRepository
import com.example.moviecatalogue.service.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogueDetailViewModel(private val mediaRepository: MediaRepository) :
    ViewModel() {
    private val _media = MutableLiveData<Status<MediaQuery.Media>>()
    val media: LiveData<Status<MediaQuery.Media>> get() = _media

    fun getCatalogueDetail(mediaId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _media.postValue(mediaRepository.getMedia(mediaId))
        }
    }
}