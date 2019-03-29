package com.freemimp.android.aroundme.data

import com.google.gson.annotations.SerializedName


data class VenueByPlaceResponseModel(
        @SerializedName("meta")
        val meta: Meta,
        @SerializedName("response")
        val response: Response
)

data class Meta(
        @SerializedName("code")
        val code: Int,
        @SerializedName("requestId")
        val requestId: String
)

data class Response(
        @SerializedName("groups")
        val groups: List<Group>,
        @SerializedName("totalResults")
        val totalResults: Int
)

data class Group(
        @SerializedName("items")
        val items: List<Item>,
        @SerializedName("name")
        val name: String,
        @SerializedName("type")
        val type: String
)

data class Item(
        @SerializedName("venue")
        val venue: Venue
)

data class Venue(
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("id")
        val id: String,
        @SerializedName("location")
        val location: Location,
        @SerializedName("name")
        val name: String,
        @SerializedName("stats")
        val stats: Stats,
        @SerializedName("verified")
        val verified: Boolean
)

data class Location(
        @SerializedName("formattedAddress")
        val formattedAddress: List<String>
)


data class Category(
        @SerializedName("icon")
        val icon: Icon,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String
)

data class Icon(
        @SerializedName("prefix")
        val prefix: String,
        @SerializedName("suffix")
        val suffix: String
)

data class Stats(
        @SerializedName("checkinsCount")
        val checkinsCount: Int,
        @SerializedName("tipCount")
        val tipCount: Int,
        @SerializedName("usersCount")
        val usersCount: Int,
        @SerializedName("visitsCount")
        val visitsCount: Int
)