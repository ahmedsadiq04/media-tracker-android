package edu.metrostate.ics342.mediatracker.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.model.AddQuote
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.Review
import edu.metrostate.ics342.mediatracker.data.network.DefaultMediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MediaDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val mediaRepository = DefaultMediaRepository(DefaultSessionRepository(application))

    //is this even needed?
    private val _mediaId = MutableStateFlow(-1)
    val mediaId: StateFlow<Int> = _mediaId.asStateFlow()

    private val _mediaDetail = MutableStateFlow<Media?>(null)
    val mediaDetail: StateFlow<Media?> = _mediaDetail.asStateFlow()

    private val _error = MutableStateFlow<Boolean>(false) //only when the media could not be found
    val mediaError: StateFlow<Boolean> = _error.asStateFlow()

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    //load the media after setting ID
    fun setMediaId(id: Int) {
        //clear all defaults
        _mediaId.value = id
        _error.value = false
        _mediaDetail.value = null
        _reviews.value = emptyList()

        viewModelScope.launch {
            try {
                val response = mediaRepository.media(id)

                if (response.isSuccessful) {
                    _mediaDetail.value = response.body()

                    //load reviews here too
                    _reviews.value = mediaRepository.reviews(id, 20) //default is first 20
                } else {
                    // Errors from API
                    _error.value = true //could not find
                }
            } catch (e: Exception) {
                // on Network exceptions
                _error.value = true //could not find - same for network errors
            }

            //Final check if mediaDetail is not null then its valid
            if(mediaDetail.value != null) {
                _error.value = false
            }
        }
    }

    //load reviews, next section and continues
    fun loadReviews(mediaId: Int, limit: Int = 20, after: String? = null) {
        viewModelScope.launch {
            val newReviews = mediaRepository.reviews(mediaId, limit, after)
            _reviews.value += newReviews
        }
    }

    //TODO: notification of some kind for this instead of logs
    fun wantTo(mediaId: Int) {
        viewModelScope.launch {
            val response = mediaRepository.WantTo(mediaId)
            if(response.isSuccessful) {
                Log.i("MediaDetailViewModel", "Saved Media as 'want to'")
            } else {
                Log.e("MediaDetailViewModel", "Error when saving media as 'want to', ${response.errorBody()?.toString()}")
            }
        }
    }

    fun favorite(mediaId: Int) {
        viewModelScope.launch {
            val response = mediaRepository.Favorite(mediaId)
            if(response.isSuccessful) {
                Log.i("MediaDetailViewModel", "Saved Media as 'favorite'")
            } else {
                Log.e("MediaDetailViewModel", "Error when saving media as 'favorite', ${response.errorBody()?.toString()}")
            }
        }
    }

    fun CreateQuote(MediaID: Int, Text: String, pageNumber: Int?, Public: Boolean) {
        viewModelScope.launch {
            val response = mediaRepository.CreateQuote(MediaID, Text, pageNumber, Public)

            if(response.isSuccessful) {
                Log.i("Quote", "Saved Quote: ${Text}, Page: ${pageNumber}, Public: ${Public}")
            } else {
                Log.e("Quote", "Error creating a quote: ${response.errorBody()?.toString()}")
            }
        }
    }
}
