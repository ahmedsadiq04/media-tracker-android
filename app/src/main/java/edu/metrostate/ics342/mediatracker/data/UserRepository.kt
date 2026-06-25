package edu.metrostate.ics342.mediatracker.data

import edu.metrostate.ics342.mediatracker.data.model.CreateUserRequest
import edu.metrostate.ics342.mediatracker.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

const val baseURL = "https://wjtzkgpxmxtzcczzbvrz.supabase.co/functions/v1/"

class UserRepository {
    private val json = Json { ignoreUnknownKeys = true }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private val api: ApiService = Retrofit.Builder()
        .client(client)
        .baseUrl(baseURL)
        .addConverterFactory(
            json.asConverterFactory(
                "application/json; charset=utf-8".toMediaType()))
        .build()
        .create(ApiService::class.java)

    //Create an account, if valid - returns true
    //Otherwise, false
    suspend fun createAccount(
        displayName: String,
        username: String,
        email: String,
        password: String
    ): APIResult<Unit> {
        val createUserRequest = CreateUserRequest(
            email = email,
            password = password,
            username = username,
            displayName = displayName,
            clientId = BuildConfig.API_CLIENT_ID,
            clientSecret = BuildConfig.API_CLIENT_SECRET
        )

        var response = api.createUser(createUserRequest)
        if(response.isSuccessful) return APIResult.Success(Unit)
        if(response.code() == 409) return APIResult.Error(edu.metrostate.ics342.mediatracker.R.string.error_api_auth)
        return APIResult.Error(edu.metrostate.ics342.mediatracker.R.string.error_api_generic)
    }
}