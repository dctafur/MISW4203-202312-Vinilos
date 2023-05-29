package com.vinyls.performers

import com.vinyls.albums.Album
import com.vinyls.albums.AlbumsApi

class PerformersRepository {

    suspend fun getPerformers(): List<Performer> {
        val performers = PerformersCacheManager.getInstance().getPerformers()
        if (performers.isNullOrEmpty()) {
            val items = PerformersApi.retrofitService.getPerformers()
            PerformersCacheManager.getInstance().addPerformers(items)
            return items
        }
        return performers
    }

    suspend fun getPerformer(id: Int): Performer {
        val performers = PerformersCacheManager.getInstance().getPerformers()
        if (performers.isNullOrEmpty())
            return PerformersApi.retrofitService.getPerformer(id)
        return performers.find { item: Performer -> item.id == id }!!
    }

    suspend fun getPerformerAlbums(performer: Performer): List<Album> {
        val albums = ArrayList<Album>()
        for (item: Album in performer.albums)
            albums.add(AlbumsApi.retrofitService.getAlbum(item.id!!))
        return albums
    }
}