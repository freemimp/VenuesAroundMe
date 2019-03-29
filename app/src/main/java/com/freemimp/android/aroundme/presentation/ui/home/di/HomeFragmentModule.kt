package com.freemimp.android.aroundme.presentation.home.di

import com.freemimp.android.aroundme.presentation.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {
    @ContributesAndroidInjector
    abstract fun findHomeFragment(): HomeFragment

}