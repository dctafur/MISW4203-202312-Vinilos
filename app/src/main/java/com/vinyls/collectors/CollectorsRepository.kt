package com.vinyls.collectors

import com.vinyls.albums.Album
import com.vinyls.albums.AlbumsApi

class CollectorsRepository {

    suspend fun getCollectors(): List<Collector> {
        return CollectorsApi.retrofitService.getCollectors()
    }

    suspend fun getCollector(id: Int): Collector {
        return CollectorsApi.retrofitService.getCollector(id)
    }

    suspend fun getCollectorAlbums(collector: Collector): List<Album> {
        val albums = ArrayList<Album>()

        for (item: CollectorAlbum in collector.collectorAlbums) {
            albums.add(AlbumsApi.retrofitService.getAlbum(item.id))
        }

        return albums
    }
}