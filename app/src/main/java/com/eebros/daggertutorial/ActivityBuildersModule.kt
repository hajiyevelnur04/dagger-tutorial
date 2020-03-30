package com.eebros.daggertutorial

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainActivityViewModel::class])
    abstract fun mainActivity(): MainActivity
}