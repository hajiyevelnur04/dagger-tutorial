package com.eebros.daggertutorial.base

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

interface BaseViewModelInputs {
}

interface BaseViewModelOutputs {
    fun onError(): Observable<Int>
    fun onSuccess(): Observable<Boolean>
}

open class BaseViewModel : ViewModel(), BaseViewModelInputs, BaseViewModelOutputs {

    protected val subscriptions = CompositeDisposable()
    protected val error: Subject<Int> = PublishSubject.create()

    protected val success: Subject<Boolean> = PublishSubject.create()

    open val inputs: BaseViewModelInputs
        get() = this

    open val outputs: BaseViewModelOutputs
        get() = this

    override fun onCleared() {
        subscriptions.clear()
    }

    override fun onError() = error

    override fun onSuccess() = success
}