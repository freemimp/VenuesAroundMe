package com.freemimp.android.aroundme.data

import com.freemimp.android.aroundme.domain.Address
import com.freemimp.android.aroundme.domain.Maybe
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.domain.VenueRepository
import retrofit2.Response
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class FourSquareVenueService @Inject constructor(private val api: FourSquareApi) : VenueRepository {


    override suspend fun findVenues(place: String, limit: Int): Maybe<List<Venue>> {
        return try {
            val request = api.getVenues(place, limit).await()

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

    override suspend fun getVenuesFrom(offset: Int, place: String): Maybe<List<Venue>> {
        return try {
            val request = api.getVenuesFrom(place, offset).await()

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
            ?.flatMap {
                it.items.map { item ->
                    Venue(
                        item.venue.id,
                        item.venue.name,
                        item.venue.categories[0].name,
                        Address(
                            item.venue.location.address,
                            item.venue.location.cc,
                            item.venue.location.city,
                            item.venue.location.country,
                            item.venue.location.postalCode,
                            item.venue.location.state
                        )
                    )
                }
            } ?: listOf()
    }
}