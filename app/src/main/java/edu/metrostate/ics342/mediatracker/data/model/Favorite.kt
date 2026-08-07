package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteItem(
    val userId: String,
    val mediaId: Int,
    val createdAt: String,
    val media: Media? = null
)

@Serializable
data class FavoriteRequest(
    val mediaId: Int,
)