package com.vinyls.collectors.aggregate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.albums.Album
import com.vinyls.albums.AlbumsRepository
import com.vinyls.collectors.CollectorsRepository

enum class CollectorsAggregateStatus { LOADING, ERROR, DONE }

class CollectorsAggregateViewModel : ViewModel() {

    private val _albumsRepository = AlbumsRepository()
    private val _collectorsRepository = CollectorsRepository()

    private val _status = MutableLiveData<CollectorsAggregateStatus>()
    val status: LiveData<CollectorsAggregateStatus> = _status

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            _status.value = CollectorsAggregateStatus.LOADING
            try {
                _albums.value = _albumsRepository.getAlbums()
            } catch (e: Exception) {
                _albums.value = listOf()
                _status.value = CollectorsAggregateStatus.ERROR
            }
        }
    }

    fun aggregateAlbum(collectorId: Int, album: Album) {
        viewModelScope.launch {
            _status.value = CollectorsAggregateStatus.LOADING
            try {
                _collectorsRepository.aggregateAlbum(collectorId, album)
                _status.value = CollectorsAggregateStatus.DONE
            } catch (e: Exception) {
                _status.value = CollectorsAggregateStatus.ERROR
            }
        }
    }
}