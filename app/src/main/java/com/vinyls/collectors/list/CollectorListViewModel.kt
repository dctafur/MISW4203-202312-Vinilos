package com.vinyls.collectors.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.collectors.Collector
import com.vinyls.collectors.CollectorRepository

enum class CollectorStatus { LOADING, ERROR, DONE }

class CollectorListViewModel : ViewModel() {

    private val _collectorRepository = CollectorRepository()

    private val _status = MutableLiveData<CollectorStatus>()
    val status: LiveData<CollectorStatus> = _status

    private val _collectors = MutableLiveData<List<Collector>>()
    val collectors: LiveData<List<Collector>> = _collectors

    init {
        getCollectors()
    }

    private fun getCollectors() {
        viewModelScope.launch {
            _status.value = CollectorStatus.LOADING
            try {
                _collectors.value = _collectorRepository.getCollectors()
                _status.value = CollectorStatus.DONE
            } catch (e: Exception) {
                _collectors.value = listOf()
                _status.value = CollectorStatus.ERROR
            }
        }
    }
}