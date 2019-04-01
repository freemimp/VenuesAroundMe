package com.freemimp.android.aroundme.data

import com.freemimp.android.aroundme.domain.Address
import com.freemimp.android.aroundme.domain.Maybe
import com.freemimp.android.aroundme.domain.Venue
import com.freemimp.android.aroundme.domain.VenueRepository
import retrofit2.Response
import javax.inject.Inject

class FourSquareVenueService @Inject constructor(private val api: FourSquareApi) : VenueRepository {
    override suspend fun findVenues(place: String, limit: Int, offset: Int): Maybe<List<Venue>> {
        return try {
            val request = api.getVenues(limit, place, limit).await()

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
        return request.body()?.let {  it.response.groups.flatMap { group ->
                group.items.map { item -> Venue(item.venue.id, item.venue.name, item.venue.categories[0].name,
                        Address(item.venue.location.address,
                            item.venue.location.cc,
                            item.venue.location.city,
                            item.venue.location.country,
                            item.venue.location.postalCode,
                            item.venue.location.state))
                }
            }} ?: listOf()
    }
}