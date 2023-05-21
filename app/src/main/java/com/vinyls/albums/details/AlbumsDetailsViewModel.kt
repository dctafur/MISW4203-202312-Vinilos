package com.vinyls.albums.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.albums.Album
import com.vinyls.albums.AlbumsRepository

enum class AlbumDetailsStatus { LOADING, ERROR, DONE }

class AlbumsDetailsViewModel : ViewModel() {

    private val _albumsRepository = AlbumsRepository()

    private val _status = MutableLiveData<AlbumDetailsStatus>()
    val status: LiveData<AlbumDetailsStatus> = _status

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album> = _album

    fun getAlbum(id: Int) {
        viewModelScope.launch {
            _status.value = AlbumDetailsStatus.LOADING
            try {
                _album.value = _albumsRepository.getAlbum(id)
                _status.value = AlbumDetailsStatus.DONE
            } catch (e: Exception) {
                _status.value = AlbumDetailsStatus.ERROR
            }
        }
    }
}