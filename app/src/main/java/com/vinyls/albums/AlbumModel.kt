package com.vinyls.albums

data class Album(
    val id: Int?,
    val cover: String,
    val name: String,
    val description: String,
    val releaseDate: String,
    val genre: String,
    val recordLabel: String,
)
