package com.freemimp.android.aroundme.domain

interface VenueRepository {
   suspend fun findVenues(place: String, limit: Int): Maybe<List<Venue>>
   suspend fun getVenuesFrom(offset: Int, place: String): Maybe<List<Venue>>
}