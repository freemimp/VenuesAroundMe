package com.freemimp.android.aroundme.data

import com.freemimp.android.aroundme.domain.Maybe
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.domain.VenueRepository
import retrofit2.Response
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class FourSquareVenueService @Inject constructor(private val api: FourSquareApi) : VenueRepository {


    override fun findVenues(place: String): Maybe<List<Venue>> {
       return try {
           val request = api.getVenues(place)

           if (request.isSuccessful) {
               val venues = mapToDomainModel(request)
               Maybe.Success(venues)
           } else {
               Maybe.Error(RuntimeException(request.errorBody()?.string()))
           }
       } catch (e: Exception) {
           Maybe.Error(e)
       }
    }


    private fun mapToDomainModel(request: Response<VenueByPlaceResponseModel>): List<Venue> {
        return request.body()?.response?.groups
                ?.map {
                    it.items.map { item ->
                        Venue(item.venue.name,
                                item.venue.categories[0].name,
                                item.venue.location.formattedAddress.toString())
                    }[0]
                }!!
    }
}