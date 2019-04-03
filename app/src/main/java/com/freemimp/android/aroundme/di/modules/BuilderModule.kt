package com.freemimp.android.aroundme.di.modules

import com.freemimp.android.aroundme.presentation.MainActivity
import com.freemimp.android.aroundme.presentation.ui.home.di.HomeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])

    abstract fun bindMainActivity(): MainActivity
}