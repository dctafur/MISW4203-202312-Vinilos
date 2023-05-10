package com.vinyls.albums

class AlbumsCacheManager {

    companion object {

        @Volatile
        private var instance: AlbumsCacheManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: AlbumsCacheManager().also { instance = it }
            }
    }

    private var albums: ArrayList<Album>? = null

    fun addAlbum(item: Album) {
        if (albums == null) albums = ArrayList()
        albums!!.add(item)
    }

    fun addAlbums(items: List<Album>) {
        if (albums == null) albums = ArrayList()
        albums!!.addAll(items)
    }

    fun getAlbums(): List<Album>? {
        return albums
    }
}