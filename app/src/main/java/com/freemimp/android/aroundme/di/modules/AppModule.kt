package com.freemimp.android.aroundme.di.modules

import android.content.Context
import com.freemimp.android.aroundme.PlacesApp
import com.freemimp.android.aroundme.di.annotations.AppContext
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

}
