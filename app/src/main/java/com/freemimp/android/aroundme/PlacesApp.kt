package com.freemimp.android.aroundme

import com.freemimp.android.aroundme.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class PlacesApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.builder().application(this).build()
    }
}