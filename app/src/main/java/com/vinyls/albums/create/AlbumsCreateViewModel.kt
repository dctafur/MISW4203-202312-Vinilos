package com.vinyls.albums.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyls.albums.Album
import com.vinyls.albums.AlbumsRepository
import kotlinx.coroutines.launch

enum class AlbumsCreateStatus { LOADING, ERROR, DONE }

class AlbumsCreateViewModel : ViewModel() {

    private val _albumsRepository = AlbumsRepository()

    private val _status = MutableLiveData<AlbumsCreateStatus>()
    val status: LiveData<AlbumsCreateStatus> = _status

    fun createAlbum(album: Album) {
        viewModelScope.launch {
            _status.value = AlbumsCreateStatus.LOADING
            try {
                _albumsRepository.createAlbum(album)
                _status.value = AlbumsCreateStatus.DONE
            } catch (e: Exception) {
                _status.value = AlbumsCreateStatus.ERROR
            }
        }
    }
}