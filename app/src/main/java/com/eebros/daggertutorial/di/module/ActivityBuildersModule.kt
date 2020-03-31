package com.eebros.daggertutorial.di.module

import com.eebros.daggertutorial.MainActivity
import com.eebros.daggertutorial.MainActivityViewModel
import com.eebros.daggertutorial.di.module.sub.MainModule
import com.eebros.daggertutorial.di.scope.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainActivityViewModel::class])
    abstract fun mainActivity(): MainActivity
}