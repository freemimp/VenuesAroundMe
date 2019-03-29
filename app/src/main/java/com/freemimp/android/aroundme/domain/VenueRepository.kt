package com.freemimp.android.aroundme.domain

interface VenueRepository {
    fun findVenues(place: String): Maybe<List<Venue>>
}