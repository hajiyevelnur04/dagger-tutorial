package com.eebros.daggertutorial.di.module

import com.eebros.daggertutorial.MainActivity
import com.eebros.daggertutorial.presentation.activity.Main3Activity
import com.eebros.daggertutorial.di.module.sub.MainModule
import com.eebros.daggertutorial.di.module.viewmodel.MainModuleViewModel
import com.eebros.daggertutorial.di.scope.MainScope
import com.eebros.daggertutorial.ui.home.HomeFragment
import com.eebros.daggertutorial.presentation.fragment.TestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun mainActivity(): MainActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun mainFragment(): HomeFragment

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun testFragment(): TestFragment


}