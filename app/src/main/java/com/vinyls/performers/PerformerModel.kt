package com.vinyls.performers
import com.vinyls.albums.Album

data class Performer(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val albums: List<Album>
)
