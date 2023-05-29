package com.vinyls.collectors.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.albums.Album
import com.vinyls.collectors.Collector
import com.vinyls.collectors.CollectorsRepository

enum class CollectorDetailsStatus { LOADING, ERROR, DONE }

class CollectorsDetailsViewModel : ViewModel() {

    private val _collectorsRepository = CollectorsRepository()

    private val _status = MutableLiveData<CollectorDetailsStatus>()
    val status: LiveData<CollectorDetailsStatus> = _status

    private val _collector = MutableLiveData<Collector>()
    val collector: LiveData<Collector> = _collector

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    fun getCollector(id: Int) {
        viewModelScope.launch {
            _status.value = CollectorDetailsStatus.LOADING
            try {
                _collector.value = _collectorsRepository.getCollector(id)
                _albums.value = _collectorsRepository.getCollectorAlbums(id)
                _status.value = CollectorDetailsStatus.DONE
            } catch (e: Exception) {
                _status.value = CollectorDetailsStatus.ERROR
            }
        }
    }
}