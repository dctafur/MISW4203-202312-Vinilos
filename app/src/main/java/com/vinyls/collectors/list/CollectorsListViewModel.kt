package com.vinyls.collectors.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.collectors.Collector
import com.vinyls.collectors.CollectorsRepository

enum class CollectorsListStatus { LOADING, ERROR, DONE }

class CollectorsListViewModel : ViewModel() {

    private val _collectorsRepository = CollectorsRepository()

    private val _status = MutableLiveData<CollectorsListStatus>()
    val status: LiveData<CollectorsListStatus> = _status

    private val _collectors = MutableLiveData<List<Collector>>()
    val collectors: LiveData<List<Collector>> = _collectors

    init {
        getCollectors()
    }

    private fun getCollectors() {
        viewModelScope.launch {
            _status.value = CollectorsListStatus.LOADING
            try {
                _collectors.value = _collectorsRepository.getCollectors()
                _status.value = CollectorsListStatus.DONE
            } catch (e: Exception) {
                _collectors.value = listOf()
                _status.value = CollectorsListStatus.ERROR
            }
        }
    }
}