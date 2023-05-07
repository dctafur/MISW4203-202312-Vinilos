package com.vinyls.collectors

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
)