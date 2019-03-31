package com.freemimp.android.aroundme.presentation.pagination

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PositionalDataSource
import com.freemimp.android.aroundme.domain.FindVenues
import com.freemimp.android.aroundme.domain.Maybe
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VenueDataSource @Inject constructor(private val place: String, private val findVenues: FindVenues) :
    PositionalDataSource<Venue>() {

    val errors = MutableLiveData<Event<Throwable>>()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Venue>) {
        val startPosition = params.startPosition


                CoroutineScope(Dispatchers.IO).launch {
                    val venuesResponse: Maybe<List<Venue>>
                            = findVenues.findVenuesFrom(startPosition, place)
                    when (venuesResponse) {
                        is Maybe.Success<List<Venue>> -> callback.onResult(venuesResponse.data)
                        is Maybe.Error<*> -> {
                            errors.postValue(Event(venuesResponse.error))
//                Error { (loadRange(params, callback)) }.retry()
                        }
                    }
                }

    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Venue>) {
        val position = params.requestedStartPosition
        val size = params.pageSize

                CoroutineScope(Dispatchers.IO).launch {
                    val venuesResponse: Maybe<List<Venue>> = findVenues.findVenues(place, size)
                    when (venuesResponse) {
                        is Maybe.Success<List<Venue>> -> callback.onResult(venuesResponse.data, position, size)
                        is Maybe.Error<*> -> {
                            errors.postValue(Event(venuesResponse.error))
//                Error { (loadInitial(params, callback)) }.retry()
                        }
                    }
                }
    }




    class Error(private val retry: () -> Any) {
        fun retry() {
            retry.invoke()
        }
    }
}