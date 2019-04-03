package com.freemimp.android.aroundme.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FourSquareApi {

    @GET("explore")
    fun getVenuesAsync(@Query("limit") limit: Int,
                       @Query("near") place: String,
                       @Query("offset") offset: Int = 0): Deferred<Response<VenueByPlaceResponseModel>>
}