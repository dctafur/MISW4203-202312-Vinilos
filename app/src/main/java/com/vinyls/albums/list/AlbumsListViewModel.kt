package com.vinyls.albums.list

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyls.R
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.albums.Album
import com.vinyls.albums.AlbumsRepository

enum class AlbumsListStatus { LOADING, ERROR, DONE }

class AlbumsListViewModel : ViewModel() {

    private val _albumsRepository = AlbumsRepository()

    private val _status = MutableLiveData<AlbumsListStatus>()
    val status: LiveData<AlbumsListStatus> = _status

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            _status.value = AlbumsListStatus.LOADING
            try {
                _albums.value = _albumsRepository.getAlbums()
                _status.value = AlbumsListStatus.DONE
            } catch (e: Exception) {
                _albums.value = listOf()
                _status.value = AlbumsListStatus.ERROR
            }
        }
    }
}