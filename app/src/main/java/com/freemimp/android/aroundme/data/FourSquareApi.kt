package com.freemimp.android.aroundme.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FourSquareApi {

    @GET("explore")
    fun getVenues(@Query("near") place: String,
                  @Query("limit") limit: Int): Response<VenueByPlaceResponseModel>

    @GET("explore")
    fun getVenuesFrom(@Query("offset") offset: Int,
                      @Query("near") place: String): Response<VenueByPlaceResponseModel>
}