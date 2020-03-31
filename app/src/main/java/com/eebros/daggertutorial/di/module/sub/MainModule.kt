package com.eebros.daggertutorial.di.module.sub

import com.eebros.daggertutorial.repository.MainRepository
import com.eebros.daggertutorial.repository.MainRepositoryType
import com.eebros.daggertutorial.di.scope.MainScope
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @MainScope
    fun providesMainRepository(serviceProvider: MainApiServiceProvider): MainRepositoryType {
        return MainRepository(
            serviceProvider = serviceProvider
        )
    }
}