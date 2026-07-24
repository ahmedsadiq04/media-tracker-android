package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.R
import edu.metrostate.ics342.mediatracker.data.APIResult
import edu.metrostate.ics342.mediatracker.data.SessionRepository
import edu.metrostate.ics342.mediatracker.data.model.FavoriteItem
import edu.metrostate.ics342.mediatracker.data.model.FavoriteRequest
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryRequest
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.Review
import retrofit2.Response

data class MediaPage(
    val items: List<Media>,
    val nextCursor: String?,
    val hasMore: Boolean
)

class DefaultMediaRepository(sessionRepository: SessionRepository) {

    private val api = RetrofitInstance.mediaApiService(sessionRepository)

    suspend fun search(query: String, type: String?, after: String?): MediaPage {
        val response = api.searchMedia(
            query = query.ifBlank { null },
            type  = type?.ifBlank { null },
            after = after
        )
        val items      = response.body() ?: emptyList()
        val nextCursor = response.headers()["X-Next-Cursor"]
        val hasMore    = response.headers()["X-Has-More"] == "true"
        return MediaPage(items, nextCursor, hasMore)
    }

    suspend fun media(mediaID: Int): Response<Media> {
        return api.getMedia(mediaID)
    }

    suspend fun reviews(MediaID: Int, limit: Int, after: String? = null): List<Review> {
        val response = api.getReview(
            mediaID = MediaID,
            limit = limit,
            after = after
        )

        if(response.isSuccessful) {
            val data = response.body()
            if(data != null) return data
            return emptyList()
        }

        return emptyList()
    }

    suspend fun WantTo(MediaID: Int): Response<LibraryItem> {
        return api.addToLibrary(LibraryRequest(mediaId = MediaID, status = "want_to"))
    }

    suspend fun Favorite(MediaID: Int): Response<FavoriteItem> {
        return api.addToFavorite(FavoriteRequest(mediaId = MediaID))
    }
}
