package com.freemimp.android.aroundme.testdata

import com.freemimp.android.aroundme.data.Category
import com.freemimp.android.aroundme.data.Group
import com.freemimp.android.aroundme.data.Icon
import com.freemimp.android.aroundme.data.Item
import com.freemimp.android.aroundme.data.Location
import com.freemimp.android.aroundme.data.Meta
import com.freemimp.android.aroundme.data.Stats
import com.freemimp.android.aroundme.data.Venue
import com.freemimp.android.aroundme.data.VenueByPlaceResponseModel
import com.freemimp.android.aroundme.domain.Address
import com.freemimp.android.aroundme.domain.Maybe
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

object TestData {

    val request: Response<VenueByPlaceResponseModel> = Response
            .success(VenueByPlaceResponseModel(Meta(200,""),
                    com.freemimp.android.aroundme.data.Response(
                            listOf(Group(
                                    listOf(Item(Venue(
                                            listOf(Category(Icon("",""),"","Park")),
                                            "", Location("","","","","",""),
                                            "Hyde Park",
                                            Stats(1,1,1,1),false))),
                                    "Park","")),1)))

    const val query = "London"
    const val limit = 10
    const val offset = 20

    val errorResponseBody: ResponseBody = ResponseBody.create(MediaType.parse("text/plain"), "Mock Error response")

    private val listOfVenues = listOf(com.freemimp.android.aroundme.domain.Venue("", "Hyde Park", "Park", Address("1 london street", "", "", "", "SW7 8GH", "")))

    val successResponse = Maybe.Success(listOfVenues)

}

