package com.freemimp.android.aroundme.data

import com.freemimp.android.aroundme.domain.Maybe
import com.freemimp.android.aroundme.testdata.TestData
import com.freemimp.android.aroundme.testdata.TestData.errorResponseBody
import com.freemimp.android.aroundme.testdata.TestData.limit
import com.freemimp.android.aroundme.testdata.TestData.offset
import com.freemimp.android.aroundme.testdata.TestData.query
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class FourSquareVenueServiceTest {

    @Mock
    lateinit var retrofitCall: Deferred<Response<VenueByPlaceResponseModel>>

    @Mock
    lateinit var fourSquareApi: FourSquareApi

    @InjectMocks
    lateinit var fourSquareVenueService: FourSquareVenueService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun givenErrorResponseWhenApiGetVenuesThenMaybeErrorWithErrorBody() {
        //Given
        val errorBody = "Mock Error response"
        runBlocking {
            whenever(retrofitCall.await()).then { Response.error<String>(400, errorResponseBody) }
        }
        whenever(fourSquareApi.getVenuesAsync(limit, query, offset)).thenReturn(retrofitCall)
        //When
        val response = runBlocking { fourSquareVenueService.findVenues(query, limit,offset) }

        //Then
        assert(response is Maybe.Error ) { "$response is not of type Maybe.Error" }
        assert((response as Maybe.Error).error.localizedMessage.contains(errorBody) ) { "${response.error.localizedMessage} is not in $errorBody" }
    }

    @Test
    fun givenExceptionWhenApiGetVenuesThenMaybeError() {
        //Given
        runBlocking {
            doThrow(RuntimeException("RuntimeException")).`when`(retrofitCall).await()
        }
        whenever(fourSquareApi.getVenuesAsync(limit, query, offset)).thenReturn(retrofitCall)
        //When
        val response = runBlocking { fourSquareVenueService.findVenues(query, limit,offset) }

        //Then
        assert(response is Maybe.Error ) { "$response is not of type Maybe.Error" }
        assert((response as Maybe.Error).error.localizedMessage.contains("RuntimeException") ) { "${response.error.localizedMessage} is not Runtime Exception" }
    }

    @Test
    fun givenSuccessfulResponseWhenApiGetVenuesThenMaybeSuccess() {
        //Given
        runBlocking {
            whenever(retrofitCall.await()).thenReturn(TestData.request)
        }
        whenever(fourSquareApi.getVenuesAsync(limit, query, offset)).thenReturn(retrofitCall)
        //When
        val response = runBlocking { fourSquareVenueService.findVenues(query, limit, offset) }

        //Then
        assert(response is Maybe.Success) { "$response is not Success" }
        assert((response as Maybe.Success).data[0].name == "Hyde Park") {"${response.data[0].name} is not Hyde Park"}
    }
}