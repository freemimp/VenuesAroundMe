package com.freemimp.android.aroundme.domain

interface VenueRepository {
    fun findVenues(place: String, limit: Int): Maybe<List<Venue>>
    fun getVenuesFrom(offset: Int, place: String): Maybe<List<Venue>>
}