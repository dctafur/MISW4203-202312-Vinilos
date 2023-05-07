package com.vinyls.performers.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

import com.vinyls.performers.Performer
import com.vinyls.performers.PerformerRepository

enum class PerformerStatus { LOADING, ERROR, DONE }

class PerformerListViewModel : ViewModel() {

    private val _performerRepository = PerformerRepository()

    private val _status = MutableLiveData<PerformerStatus>()
    val status: LiveData<PerformerStatus> = _status

    private val _performers = MutableLiveData<List<Performer>>()
    val performers: LiveData<List<Performer>> = _performers

    init {
        getPerformers()
    }

    private fun getPerformers() {
        viewModelScope.launch {
            _status.value = PerformerStatus.LOADING
            try {
                _performers.value = _performerRepository.getPerformers()
                _status.value = PerformerStatus.DONE
            } catch (e: Exception) {
                _performers.value = listOf()
                _status.value = PerformerStatus.ERROR
            }
        }
    }
}