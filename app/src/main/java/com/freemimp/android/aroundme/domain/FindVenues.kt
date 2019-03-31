package com.freemimp.android.aroundme.domain

import javax.inject.Inject

class FindVenues @Inject constructor(private val repository: VenueRepository) {

    fun findVenues(place: String, limit: Int): Maybe<List<Venue>> {
        return repository.findVenues(place, limit)

    }

    fun findVenuesFrom(offset: Int, place: String): Maybe<List<Venue>> {
        return repository.getVenuesFrom(offset, place)
    }
}