package com.freemimp.android.aroundme.domain

interface VenueRepository {
    suspend fun findVenues(place: String, limit: Int, offset: Int = 0): Maybe<List<Venue>>
}