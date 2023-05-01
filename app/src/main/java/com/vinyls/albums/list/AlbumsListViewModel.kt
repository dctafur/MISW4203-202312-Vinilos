package com.vinyls.albums.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.albums.Album
import com.vinyls.albums.AlbumsRepository

enum class AlbumsStatus { LOADING, ERROR, DONE }

class AlbumsViewModel : ViewModel() {

    private val _albumsRepository = AlbumsRepository()

    private val _status = MutableLiveData<AlbumsStatus>()
    val status: LiveData<AlbumsStatus> = _status

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            _status.value = AlbumsStatus.LOADING
            try {
                _albums.value = _albumsRepository.getAlbums()
                _status.value = AlbumsStatus.DONE
            } catch (e: Exception) {
                _albums.value = listOf()
                _status.value = AlbumsStatus.ERROR
            }
        }
    }
}