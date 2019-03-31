package com.freemimp.android.aroundme.di.modules

import android.content.Context
import com.freemimp.android.aroundme.PlacesApp
import com.freemimp.android.aroundme.data.FourSquareApi
import com.freemimp.android.aroundme.data.FourSquareVenueService
import com.freemimp.android.aroundme.di.annotations.AppContext
import com.freemimp.android.aroundme.domain.FindVenues
import com.freemimp.android.aroundme.domain.VenueRepository
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSource
import com.freemimp.android.aroundme.presentation.pagination.VenueDataSourceFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.util.concurrent.locks.ReentrantLock
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
    @Singleton
    fun providesVenueRepository(api: FourSquareApi): VenueRepository  = FourSquareVenueService(api)

    @Provides
    @Singleton
    fun providesFindVenues(repository: VenueRepository) = FindVenues(repository)

    @Provides
    fun providesVenuesDataSource(findVenues: FindVenues) = VenueDataSource("London", findVenues)

    @Provides
    fun providesVenueDataSourceFactory(venueDataSource: VenueDataSource) = VenueDataSourceFactory(venueDataSource)


}
