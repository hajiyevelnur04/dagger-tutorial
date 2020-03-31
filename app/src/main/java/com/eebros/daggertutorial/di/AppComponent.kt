package com.eebros.daggertutorial.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.eebros.daggertutorial.di.module.ActivityBuildersModule
import com.eebros.daggertutorial.di.module.AppModule
import com.eebros.daggertutorial.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<AppCompatActivity> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}