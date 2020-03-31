package com.eebros.daggertutorial.base

import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : DaggerFragment(){
    protected val subscriptions = CompositeDisposable()

}