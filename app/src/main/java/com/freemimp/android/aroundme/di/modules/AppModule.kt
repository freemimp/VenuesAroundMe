package com.freemimp.android.aroundme.di.modules

import android.content.Context
import com.freemimp.android.aroundme.PlacesApp
import com.freemimp.android.aroundme.data.FourSquareApi
import com.freemimp.android.aroundme.data.FourSquareVenueService
import com.freemimp.android.aroundme.di.annotations.AppContext
import com.freemimp.android.aroundme.domain.FindVenues
import com.freemimp.android.aroundme.domain.VenueRepository
import com.freemimp.android.aroundme.presentation.pagination.Place
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSourceFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @AppContext
    fun providesAppContext(placesApp: PlacesApp): Context = placesApp

    @Provides
    fun providesVenueRepository(api: FourSquareApi): VenueRepository  = FourSquareVenueService(api)

    @Provides
    fun providesFindVenues(repository: VenueRepository) = FindVenues(repository)

    @Provides
    fun providesVenueDataSourceFactory(place: Place, findVenues: FindVenues) = VenueDataSourceFactory(place, findVenues)

    @Provides
    @Singleton
    fun providesPlace() = Place()
}
