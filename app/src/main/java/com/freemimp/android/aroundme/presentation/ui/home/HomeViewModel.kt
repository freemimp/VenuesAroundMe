package com.freemimp.android.aroundme.presentation.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.freemimp.android.aroundme.domain.FindVenues
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSourceFactory
import com.freemimp.android.aroundme.utils.Event
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val venueDataSourceFactory: VenueDataSourceFactory, private val findVenues: FindVenues) : ViewModel() {

    val error = MutableLiveData<Event<String>>()

    private val pageListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(10)
            .build()

    val venues = LivePagedListBuilder<Int, Venue>(venueDataSourceFactory, pageListConfig).build()


    fun fetchVenues(place: String) {
        if (validPlace(place)) {
            invalidateDataSource()
        } else {
            error.postValue(Event("Please enter valid place!"))
        }
    }

    fun getSourceErrors() = venueDataSourceFactory.sources.value?.errors

    private fun validPlace(place: String?): Boolean = !place.isNullOrBlank()

    private fun invalidateDataSource() = venueDataSourceFactory.sources.value?.invalidate()


}
