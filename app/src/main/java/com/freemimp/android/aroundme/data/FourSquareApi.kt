package com.freemimp.android.aroundme.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FourSquareApi {

    @GET("explore")
    fun getVenues(@Query("near") place: String): Response<VenueByPlaceResponseModel>
}