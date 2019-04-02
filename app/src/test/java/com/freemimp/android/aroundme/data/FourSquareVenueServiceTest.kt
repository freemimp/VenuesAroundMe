package com.freemimp.android.aroundme.data

import com.freemimp.android.aroundme.BaseTest
import com.freemimp.android.aroundme.domain.Maybe
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class FourSquareVenueServiceTest : BaseTest() {

    @Test
    fun givenErrorResponseWhenApiGetVenuesThenMaybeError() {
        //Given
        runBlocking {
            whenever(retrofitCall.await()).then { Response.error<String>(400, errorResponseBody) }
        }
        whenever(fourSquareApi.getVenues(limit, query)).thenReturn(retrofitCall)
        //When
        val response = runBlocking { fourSquareVenueService.findVenues(query, limit) }

        //Then
        assert(response is Maybe.Error) { "$response is not Error" }
    }

    @Test
    fun givenSuccessfulResponseWhenApiGetVenuesThenMaybeSuccess() {
        //Given
        runBlocking {
            whenever(retrofitCall.await()).then { Response.success(responseBody) }
        }
        whenever(fourSquareApi.getVenues(limit, query)).thenReturn(any())
        //When
        val response = runBlocking { fourSquareVenueService.findVenues(query, limit) }

        //Then
        assert(response is Maybe.Success) { "$response is not Success" }
    }
}