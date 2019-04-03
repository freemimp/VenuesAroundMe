package com.freemimp.android.aroundme.domain

data class Venue(val id: String, val name: String, val venueType: String, val address: Address)

data class Address(
        val address: String?,
        val cc: String?,
        val city: String?,
        val country: String?,
        val postalCode: String?,
        val state: String?
)