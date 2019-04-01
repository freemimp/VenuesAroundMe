package com.freemimp.android.aroundme.presentation.pagination

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.freemimp.android.aroundme.domain.FindVenues
import com.freemimp.android.aroundme.domain.Venue
import javax.inject.Inject

class VenueDataSourceFactory @Inject constructor(
     private val place: Place,
     private val findVenues: FindVenues
) : DataSource.Factory<Int, Venue>() {

    val sources = MutableLiveData<VenueDataSource>()

    override fun create(): DataSource<Int, Venue> {
     val source = VenueDataSource(place, findVenues)
        sources.postValue(source)
        return source
    }
}