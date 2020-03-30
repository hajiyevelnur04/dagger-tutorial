package com.eebros.daggertutorial

import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @MainScope
    fun providesMainRepository(serviceProvider: MainApiServiceProvider): MainRepositoryType {
        return MainRepository(serviceProvider = serviceProvider)
    }
}