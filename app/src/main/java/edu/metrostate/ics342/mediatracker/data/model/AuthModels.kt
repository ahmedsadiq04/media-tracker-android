package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val email: String,
    val password: String,
    val username: String,
    val displayName: String,
    val clientId: String,
    val clientSecret: String
)

@Serializable
data class TokenRequest(
    val grantType: String,
    val email: String? = null,
    val password: String? = null,
    val refreshToken: String? = null,
    val clientId: String,
    val clientSecret: String
)

@Serializable
data class CreateUserResponse(
    var id: String,
    var email: String,
    var displayName: String,
    var bio: String?,
    var avatarUrl: String?,
    var followerCount: Int,
    var followingCount: Int,
    var trackedCount: Int,
    var isFollowing: Boolean?,
    var createdAt: String, //can be string due to it using ISO 8601
)