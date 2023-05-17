package com.vinyls.collectors

import com.vinyls.albums.Album

class CollectorsCacheManager {

    companion object {

        @Volatile
        private var instance: CollectorsCacheManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CollectorsCacheManager().also { instance = it }
            }
    }

    private var _collectors: ArrayList<Collector>? = null
    private val _albums: HashMap<Int, List<Album>> = hashMapOf()

    fun addCollector(item: Collector) {
        if (_collectors == null) _collectors = ArrayList()
        _collectors!!.add(item)
    }

    fun addCollectors(items: List<Collector>) {
        if (_collectors == null) _collectors = ArrayList()
        _collectors!!.addAll(items)
    }

    fun getCollectors(): List<Collector>? {
        return _collectors
    }

    fun addCollectorAlbums(collectorId: Int, items: List<Album>) {
        _albums[collectorId] = items
    }

    fun getCollectorAlbums(collectorId: Int): List<Album>? {
        return if (_albums.containsKey(collectorId)) _albums[collectorId] else listOf()
    }
}