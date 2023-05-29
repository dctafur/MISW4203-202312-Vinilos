package com.vinyls.collectors

import com.vinyls.albums.Album

data class Collector(
    val id: Int,
    val telephone: String,
    val name: String,
    val email: String,
    val collectorAlbums: List<CollectorAlbum>
)

data class CollectorAlbum(
    val id: Int,
    val price: Int,
    val status: String,
    val album: Album
)

data class AggregateAlbum(
    val price: Int,
    val status: String
)