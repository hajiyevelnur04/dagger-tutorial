package com.eebros.daggertutorial.base

import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject
import com.eebros.daggertutorial.di.DaggerAppComponent

class BaseApplication : DaggerApplication(), HasSupportFragmentInjector {
    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector
    }
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}