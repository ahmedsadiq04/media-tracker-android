package edu.metrostate.ics342.mediatracker.ui.quotes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.model.AddQuote
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.Quote
import edu.metrostate.ics342.mediatracker.data.model.Review
import edu.metrostate.ics342.mediatracker.data.network.DefaultMediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuotesViewModel(application: Application) : AndroidViewModel(application) {
    private val mediaRepository = DefaultMediaRepository(DefaultSessionRepository(application))

    private val _quotes = MutableStateFlow<List<Quote>>(emptyList())
    val quotes: StateFlow<List<Quote>> = _quotes.asStateFlow()

    fun getMyQuotes() {
        _quotes.value = emptyList<Quote>();
        viewModelScope.launch {
            val response = mediaRepository.GetMyQuotes()

            if (response.isSuccessful) {
                _quotes.value = response.body() ?: emptyList()
            } else {
                Log.e("Quote", "Failed to get self-user's quotes: ${response.code()}")
            }
        }
    }

    fun getPublicQuotes() {
        _quotes.value = emptyList<Quote>();
        viewModelScope.launch {
            val response = mediaRepository.GetPublicQuotes()

            if (response.isSuccessful) {
                _quotes.value = response.body() ?: emptyList()
            } else {
                Log.e("Quote", "Failed to get public quotes: ${response.code()}")
            }
        }
    }
}
