package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.FavoriteItem
import edu.metrostate.ics342.mediatracker.data.model.FavoriteRequest
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryRequest
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.Review
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaApiService {

    //allows the search of media
    @GET("media")
    suspend fun searchMedia(
        @Query("query") query: String? = null,
        @Query("type") type: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("after") after: String? = null
    ): Response<List<Media>>

    //returns detailed media
    @GET("media/{media_id}")
    suspend fun getMedia(@Path("media_id") mediaID: Int) : Response<Media>

    @GET("reviews")
    suspend fun getReview(
        @Query("mediaId") mediaID: Int = 0,
        @Query("userId") userID: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("after") after: String? = null
    ) : Response<List<Review>>


    @POST("library")
    suspend fun addToLibrary(@Body body: LibraryRequest) : Response<LibraryItem>

    @POST("favorites")
    suspend fun addToFavorite(@Body body: FavoriteRequest) : Response<FavoriteItem>
}
