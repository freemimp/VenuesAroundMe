package com.freemimp.android.aroundme.domain

import javax.inject.Inject

class FindVenues @Inject constructor(private val repository: VenueRepository) {

    suspend fun findVenues(place: String, limit: Int): Maybe<List<Venue>> {
        return repository.findVenues(place, limit)

    }

    suspend fun findVenuesFrom(limit: Int, offset: Int, place: String): Maybe<List<Venue>> {
        return repository.findVenues(place, limit, offset)
    }
}