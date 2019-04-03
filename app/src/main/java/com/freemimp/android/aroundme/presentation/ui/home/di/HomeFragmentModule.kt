package com.freemimp.android.aroundme.presentation.ui.home.di

import com.freemimp.android.aroundme.presentation.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {
    @ContributesAndroidInjector
    abstract fun findHomeFragment(): HomeFragment

}