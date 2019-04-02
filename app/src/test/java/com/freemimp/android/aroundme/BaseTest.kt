package com.freemimp.android.aroundme

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freemimp.android.aroundme.data.FourSquareApi
import com.freemimp.android.aroundme.data.FourSquareVenueService
import com.freemimp.android.aroundme.data.Group
import com.freemimp.android.aroundme.data.Meta
import com.freemimp.android.aroundme.data.VenueByPlaceResponseModel
import com.freemimp.android.aroundme.domain.Address
import com.freemimp.android.aroundme.domain.FindVenues
import com.freemimp.android.aroundme.domain.Maybe
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.domain.VenueRepository
import com.freemimp.android.aroundme.presentation.pagination.Place
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSourceFactory
import com.freemimp.android.aroundme.presentation.ui.home.HomeViewModel
import kotlinx.coroutines.Deferred
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

open class BaseTest {

    val query = "London"
    val limit = 10
    val offset = 10

    val errorResponseBody: ResponseBody = ResponseBody.create(MediaType.parse("text/plain"), "Mock Error response")
    val responseBody: ResponseBody = ResponseBody.create(MediaType.parse("text/plain"), "Mock Success response")

    @Mock
    lateinit var retrofitCall: Deferred<Response<VenueByPlaceResponseModel>>

    @Mock
    lateinit var venueRepository: VenueRepository
    @Mock
    lateinit var fourSquareApi: FourSquareApi

    val place = Place()

    @InjectMocks
    lateinit var findVenues: FindVenues

    lateinit var venueDataSourceFactory: VenueDataSourceFactory

    @InjectMocks
    lateinit var fourSquareVenueService: FourSquareVenueService

    lateinit var homeViewModel: HomeViewModel

    val listOfVenues = listOf(Venue("", "Hyde Park", "Park", Address("1 london street", "", "", "", "SW7 8GH", "")))

    val successResponse = Maybe.Success(listOfVenues)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        venueDataSourceFactory = VenueDataSourceFactory(place, findVenues)
        homeViewModel = HomeViewModel(venueDataSourceFactory)
    }
}