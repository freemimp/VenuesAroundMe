package com.freemimp.android.aroundme.presentation.ui.home

import com.freemimp.android.aroundme.BaseTest
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

class HomeViewModelTest : BaseTest() {

    @Test
    fun givenPlaceNotEmptyWhenFetchVenuesThenNoErrorPosted() {
        //Given
        place.query = "London"
        whenever(runBlocking { venueRepository.findVenues("London", 1) }).then { successResponse }


        //When
        homeViewModel.fetchVenues(place.query)
        val error = homeViewModel.error.value?.peekContent()


        //Then
        assert(homeViewModel.venues.value.isNullOrEmpty()) { "Venues list is not null" }
        assert(error == homeViewModel.error.value?.peekContent())

    }

    @Test
    fun givenNameEmptyWhenFetchVenuesThenErrorPosted() {
        //Given
        place.query = ""

        //Then
        homeViewModel.fetchVenues(place.query)
        val error = homeViewModel.error.value?.peekContent()

        //Then
        assert(error == homeViewModel.error.value?.peekContent()) { "$error is not same as ${homeViewModel.error.value?.getContentIfNotHandled()}" }
    }
}