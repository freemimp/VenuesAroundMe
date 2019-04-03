package com.freemimp.android.aroundme.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSource
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSourceFactory
import com.freemimp.android.aroundme.utils.Event
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val venueDataSourceFactory: VenueDataSourceFactory) : ViewModel() {

    val error = MutableLiveData<Event<String>>()

    val sourceError: LiveData<Event<VenueDataSource.Error>> = Transformations.switchMap(venueDataSourceFactory.sources) {
        it.errors
    }

    private val pageListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(PAGE_SIZE / 2)
            .setPageSize(PAGE_SIZE)
            .build()

    val venues = LivePagedListBuilder<Int, Venue>(venueDataSourceFactory, pageListConfig).build()


    fun fetchVenues(place: String) {
        if (validPlace(place)) {
            invalidateDataSource()
        } else {
            error.postValue(Event("Please enter valid place!"))
        }
    }

    private fun validPlace(place: String?): Boolean = !place.isNullOrBlank()

    private fun invalidateDataSource() = venueDataSourceFactory.sources.value?.invalidate()

    companion object {
        private const val PAGE_SIZE = 20
    }

}
