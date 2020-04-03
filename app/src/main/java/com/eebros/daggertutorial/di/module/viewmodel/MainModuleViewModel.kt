package com.eebros.daggertutorial.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.eebros.daggertutorial.MainActivityViewModel
import com.eebros.daggertutorial.di.scope.MainScope
import com.eebros.daggertutorial.di.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModuleViewModel {
    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}