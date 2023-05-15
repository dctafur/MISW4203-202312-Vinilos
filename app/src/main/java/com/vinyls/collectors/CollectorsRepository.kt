package com.vinyls.collectors

import com.vinyls.albums.Album
import com.vinyls.albums.AlbumsApi

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

    suspend fun getCollectorAlbums(collector: Collector): List<Album> {
        val albums = ArrayList<Album>()
        for (item: CollectorAlbum in collector.collectorAlbums)
            albums.add(AlbumsApi.retrofitService.getAlbum(item.id))
        return albums
    }
}