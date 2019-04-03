package com.freemimp.android.aroundme.presentation.pagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.freemimp.android.aroundme.domain.FindVenues
import com.freemimp.android.aroundme.domain.Maybe
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VenueDataSource(private val place: Place, private val findVenues: FindVenues) :
        PositionalDataSource<Venue>() {

    private val _error = MutableLiveData<Event<Error>>()
    val errors: LiveData<Event<Error>> = _error

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Venue>) {
        val startPosition = params.startPosition
        val loadSize = params.loadSize

        CoroutineScope(Dispatchers.IO).launch {
            val venuesResponse: Maybe<List<Venue>> = findVenues.findVenuesFrom(loadSize, startPosition, place.query)
            when (venuesResponse) {
                is Maybe.Success -> callback.onResult(venuesResponse.data)
                is Maybe.Error -> _error.postValue(Event(Error { loadRange(params, callback) }))
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Venue>) {
        val size = params.requestedLoadSize
        if (place.query.isEmpty()) return

        CoroutineScope(Dispatchers.IO).launch {
            val venuesResponse: Maybe<List<Venue>> = findVenues.findVenues(place.query, size)
            when (venuesResponse) {
                is Maybe.Success -> callback.onResult(venuesResponse.data, 0)
                is Maybe.Error -> _error.postValue(Event(Error { loadInitial(params, callback) }))
            }
        }
    }

    class Error(private val retry: () -> Any) {
        fun retry() {
            retry.invoke()
        }
    }
}