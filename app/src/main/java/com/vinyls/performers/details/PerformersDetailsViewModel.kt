package com.vinyls.performers.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.albums.Album
import com.vinyls.performers.Performer
import com.vinyls.performers.PerformersRepository

enum class PerformerDetailsStatus { LOADING, ERROR, DONE }

class PerformersDetailsViewModel : ViewModel() {

    private val _performersRepository = PerformersRepository()

    private val _status = MutableLiveData<PerformerDetailsStatus>()
    val status: LiveData<PerformerDetailsStatus> = _status

    private val _performer = MutableLiveData<Performer>()
    val performer: LiveData<Performer> = _performer

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    fun getPerformer(id: Int) {
        viewModelScope.launch {
            _status.value = PerformerDetailsStatus.LOADING
            try {
                val performer = _performersRepository.getPerformer(id)
                _albums.value = _performersRepository.getPerformerAlbums(performer)
                _performer.value = performer
                _status.value = PerformerDetailsStatus.DONE
            } catch (e: Exception) {
                _status.value = PerformerDetailsStatus.ERROR
            }
        }
    }
}