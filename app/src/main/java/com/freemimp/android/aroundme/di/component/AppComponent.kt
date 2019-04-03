package com.freemimp.android.aroundme.di.component

import com.freemimp.android.aroundme.PlacesApp
import com.freemimp.android.aroundme.di.modules.AppModule
import com.freemimp.android.aroundme.di.modules.BuilderModule
import com.freemimp.android.aroundme.di.modules.NetworkModule
import com.freemimp.android.aroundme.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, BuilderModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(placesApp: PlacesApp): Builder

        fun build(): AppComponent
    }
}