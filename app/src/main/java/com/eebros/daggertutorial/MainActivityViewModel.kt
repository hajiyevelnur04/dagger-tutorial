package com.eebros.daggertutorial

import com.eebros.daggertutorial.base.BaseViewModel
import com.eebros.daggertutorial.base.BaseViewModelInputs
import com.eebros.daggertutorial.base.BaseViewModelOutputs
import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import com.eebros.daggertutorial.usecases.GetAllCardUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface MainActivityViewModelInputs: BaseViewModelInputs{
    fun getAllCards()
}

interface MainActivityViewModelOutputs: BaseViewModelOutputs{
    fun accountsSuccess(): PublishSubject<ArrayList<GetAllCardResponseModel>>
    fun showProgress(): PublishSubject<Boolean>
}

class MainActivityViewModel @Inject constructor(private val getAllCardUseCase: GetAllCardUseCase):
    BaseViewModel(), MainActivityViewModelInputs, MainActivityViewModelOutputs {

    override val inputs: MainActivityViewModelInputs = this
    override val outputs: MainActivityViewModelOutputs = this

    private val accountsSuccess = PublishSubject.create<ArrayList<GetAllCardResponseModel>>()

    private val showProgress = PublishSubject.create<Boolean>()

    override fun getAllCards() {
        showProgress.onNext(true)
        getAllCardUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showProgress.onNext(false)
                accountsSuccess.onNext(it)
            },{
                //some error int
                showProgress.onNext(false)
                error.onNext(1992)
                it.printStackTrace()
            }).addTo(subscriptions)
    }

    override fun accountsSuccess() = accountsSuccess

    override fun showProgress() = showProgress
}