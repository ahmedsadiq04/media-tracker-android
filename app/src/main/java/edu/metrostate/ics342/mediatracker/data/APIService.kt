package edu.metrostate.ics342.mediatracker.data

import edu.metrostate.ics342.mediatracker.data.model.CreateUserRequest
import edu.metrostate.ics342.mediatracker.data.model.CreateUserResponse
import edu.metrostate.ics342.mediatracker.data.model.TokenRefreshResponse
import edu.metrostate.ics342.mediatracker.data.model.TokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

sealed interface APIResult<out T> {
    data class Success<out T>(val data: T) : APIResult<T>
    data class Error(val msgResId: Int) : APIResult<Nothing>
}

interface ApiService {
    @POST("users")
    suspend fun createUser(@Body request: CreateUserRequest): Response<Unit>

    @POST("tokens")
    suspend fun login(@Body request: TokenRequest): Response<TokenRefreshResponse>
}
