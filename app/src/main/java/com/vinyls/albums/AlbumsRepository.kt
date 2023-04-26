package com.vinyls.albums

class AlbumsRepository {

    suspend fun getAlbums(): List<Album> {
        return AlbumsApi.retrofitService.getAlbums()
    }
}