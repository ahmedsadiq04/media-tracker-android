package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddQuote (
    val mediaId: Int,
    val quoteText: String,
    val pageNumber: Int?,
    val isPublic: Boolean?,
)

@Serializable
data class Quote (
    val id: Int,
    val userId: String,
    val mediaId: Int,
    val quoteText: String,
    val pageNumber: Int,
    val isPublic: Boolean,
    val likeCount: Int,
    val createdAt: String,
    val media: Media,
)