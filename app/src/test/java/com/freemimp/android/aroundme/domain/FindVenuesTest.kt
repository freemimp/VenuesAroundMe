package com.freemimp.android.aroundme.domain

import com.freemimp.android.aroundme.testdata.TestData.limit
import com.freemimp.android.aroundme.testdata.TestData.offset
import com.freemimp.android.aroundme.testdata.TestData.query
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FindVenuesTest {

    @Mock
    lateinit var venueRepository: VenueRepository

    @InjectMocks
    lateinit var findVenues: FindVenues

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

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