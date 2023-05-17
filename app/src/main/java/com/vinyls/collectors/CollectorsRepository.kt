package com.vinyls.collectors

import com.vinyls.albums.Album

class CollectorsRepository {

    suspend fun getCollectors(): List<Collector> {
        val collectors = CollectorsCacheManager.getInstance().getCollectors()
        if (collectors.isNullOrEmpty()) {
            val items = CollectorsApi.retrofitService.getCollectors()
            CollectorsCacheManager.getInstance().addCollectors(items)
            return items
        }
        return collectors
    }

    suspend fun getCollector(id: Int): Collector {
        val collectors = CollectorsCacheManager.getInstance().getCollectors()
        if (collectors.isNullOrEmpty())
            return CollectorsApi.retrofitService.getCollector(id)
        return collectors.find { item: Collector -> item.id == id }!!
    }

    suspend fun getCollectorAlbums(id: Int): List<Album> {
        val collectorAlbums = CollectorsCacheManager.getInstance().getCollectorAlbums(id)
        if (collectorAlbums.isNullOrEmpty()) {
            val elements = CollectorsApi.retrofitService.getCollectorAlbums(id)
            val items = elements.map { item -> item.album }
            CollectorsCacheManager.getInstance().addCollectorAlbums(id, items)
            return items
        }
        return collectorAlbums
    }

    suspend fun aggregateAlbum(collectorId: Int, album: Album) {
        val body = AggregateAlbum(price = 0, status = "Active")
        val albumCollector = CollectorsApi.retrofitService.aggregateAlbum(collectorId, album.id, body)
        val collectorAlbums = getCollectorAlbums(collectorId)
        if (collectorAlbums.isEmpty()) {
            val items = listOf(albumCollector.album)
            CollectorsCacheManager.getInstance().addCollectorAlbums(collectorId, items)
        }
        val items = arrayListOf(albumCollector.album)
        items.addAll(collectorAlbums)
        CollectorsCacheManager.getInstance().addCollectorAlbums(collectorId, items)
    }
}