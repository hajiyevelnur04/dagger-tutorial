package com.eebros.daggertutorial.di.module

import com.eebros.daggertutorial.MainActivity
import com.eebros.daggertutorial.di.module.sub.MainModule
import com.eebros.daggertutorial.di.module.viewmodel.MainViewModelModule
import com.eebros.daggertutorial.di.scope.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainViewModelModule::class])
    abstract fun mainActivity(): MainActivity
}