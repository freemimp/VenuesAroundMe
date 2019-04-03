package com.freemimp.android.aroundme.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freemimp.android.aroundme.domain.FindVenues
import com.freemimp.android.aroundme.domain.VenueRepository
import com.freemimp.android.aroundme.presentation.pagination.Place
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSourceFactory
import com.freemimp.android.aroundme.testdata.TestData.successResponse
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeViewModelTest {

    @Mock
    lateinit var venueRepository: VenueRepository

    private val place = Place()

    @InjectMocks
    lateinit var findVenues: FindVenues

    private lateinit var venueDataSourceFactory: VenueDataSourceFactory

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        venueDataSourceFactory = VenueDataSourceFactory(place, findVenues)
        homeViewModel = HomeViewModel(venueDataSourceFactory)
    }

    @Test
    fun givenPlaceNotEmptyWhenFetchVenuesThenNoErrorPosted() {
        //Given
        place.query = "London"

        //When
        homeViewModel.fetchVenues(place.query)
        val error = homeViewModel.error.value?.peekContent()


        //Then
        assert(homeViewModel.venues.value.isNullOrEmpty()) { "Venues list is not null" }
        assert(error.isNullOrBlank())

    }

    @Test
    fun givenNameEmptyWhenFetchVenuesThenErrorPosted() {
        //Given
        place.query = ""
        val errorString = "Please enter valid place!"

        //Then
        homeViewModel.fetchVenues(place.query)
        val error = homeViewModel.error.value?.peekContent()

        //Then
        assert(error == errorString) { "$error is not same as $errorString" }
    }
}