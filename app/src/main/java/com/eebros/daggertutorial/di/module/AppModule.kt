package com.eebros.daggertutorial.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.eebros.daggertutorial.base.BaseActivity
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule {
    @Provides
    fun providesContext(app: Application) = app.applicationContext

    @Provides
    fun provideResources(app: Application): Resources = app.resources

    @Provides
    fun provideActivity(): BaseActivity = BaseActivity()

    @Provides
    @Reusable
    fun provideSharedPreferences(context: Context)= context.getSharedPreferences("some_test", Context.MODE_PRIVATE)
}