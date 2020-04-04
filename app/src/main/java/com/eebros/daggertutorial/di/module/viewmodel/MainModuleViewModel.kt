package com.eebros.daggertutorial.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.eebros.daggertutorial.di.scope.MainScope
import com.eebros.daggertutorial.di.scope.ViewModelKey
import com.eebros.daggertutorial.presentation.activity.MainActivityViewModel
import com.eebros.daggertutorial.presentation.fragment.MainFragmentViewModel
import com.eebros.daggertutorial.presentation.fragment.TestFragmentViewModel
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

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: MainFragmentViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(TestFragmentViewModel::class)
    abstract fun bindTestFragmentViewModel(viewModel: TestFragmentViewModel): ViewModel
}