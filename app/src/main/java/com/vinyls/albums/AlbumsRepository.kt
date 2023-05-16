package com.vinyls.albums

class AlbumsRepository {

    suspend fun getAlbums(): List<Album> {
        val albums = AlbumsCacheManager.getInstance().getAlbums()
        if (albums.isNullOrEmpty()) {
            val items = AlbumsApi.retrofitService.getAlbums()
            AlbumsCacheManager.getInstance().addAlbums(items)
            return items
        }
        return albums
    }

    suspend fun createAlbum(album: Album) {
        AlbumsCacheManager.getInstance().addAlbum(album)
    }
}