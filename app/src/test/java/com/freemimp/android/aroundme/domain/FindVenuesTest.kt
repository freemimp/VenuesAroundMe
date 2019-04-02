package com.freemimp.android.aroundme.domain

import com.freemimp.android.aroundme.BaseTest
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class FindVenuesTest : BaseTest() {


    @Test
    fun givenPlaceAndLimitWhenFindVenuesThenRepositoryFindVenuesCalledOnce() {

        //When
        runBlocking { findVenues.findVenues(query, limit) }

        //Then
        runBlocking { verify(venueRepository, Mockito.times(1)).findVenues(query, limit) }
    }

    @Test
    fun givenPlaceAndLimitAndOffsetWhenFindVenuesFromThenRepositoryFindVenuesCalledOnce() {

        //When
        runBlocking { findVenues.findVenuesFrom(limit, offset, query) }

        //Then
        runBlocking { verify(venueRepository, Mockito.times(1)).findVenues(query, limit, offset) }
    }
}