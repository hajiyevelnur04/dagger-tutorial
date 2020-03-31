package com.eebros.daggertutorial.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
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
    fun provideActivity(): AppCompatActivity = AppCompatActivity()

    @Provides
    @Reusable
    fun provideSharedPreferences(context: Context)= context.getSharedPreferences("some test", Context.MODE_PRIVATE)
}