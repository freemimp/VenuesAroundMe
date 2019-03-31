package com.freemimp.android.aroundme.presentation.ui.home

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.freemimp.android.aroundme.data.FourSquareApi
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.domain.VenueRepository
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val venueDataSourceFactory: VenueDataSourceFactory, private val venueRepository: VenueRepository, private val api: FourSquareApi) : ViewModel() {

    private val pageListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(20)
        .setPageSize(10)
        .build()

    val venues = LivePagedListBuilder<Int, Venue>(venueDataSourceFactory, pageListConfig).build()



    fun fetchVenues(place: String) {
       CoroutineScope(Dispatchers.IO).launch {
           venueRepository.findVenues(place,10)

       }

    }

    fun validPlace(place: String?): Boolean = !place.isNullOrBlank()
}
