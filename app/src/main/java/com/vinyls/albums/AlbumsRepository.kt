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
        // TODO: use the AlbumsService create album method
        AlbumsCacheManager.getInstance().addAlbum(album)
    }

    suspend fun getAlbum(id: Int): Album {
        val albums = AlbumsCacheManager.getInstance().getAlbums()
        if (albums.isNullOrEmpty())
            return AlbumsApi.retrofitService.getAlbum(id)
        return albums.find { item: Album -> item.id == id }!!
    }
}