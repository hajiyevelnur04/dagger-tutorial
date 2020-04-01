package com.eebros.daggertutorial

import android.content.SharedPreferences
import com.eebros.daggertutorial.base.BaseViewModel
import com.eebros.daggertutorial.base.BaseViewModelInputs
import com.eebros.daggertutorial.base.BaseViewModelOutputs
import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import com.eebros.daggertutorial.usecases.GetAllCardUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface MainActivityViewModelInputs: BaseViewModelInputs{
    fun getAllCards()
}

interface MainActivityViewModelOutputs: BaseViewModelOutputs{
    fun accountsSuccess(): PublishSubject<GetAllCardResponseModel>
}

class MainActivityViewModel @Inject constructor(private val getAllCardUseCase: GetAllCardUseCase):
    BaseViewModel(), MainActivityViewModelInputs, MainActivityViewModelOutputs {

    override val inputs: MainActivityViewModelInputs = this
    override val outputs: MainActivityViewModelOutputs = this

    private val accountsSuccess = PublishSubject.create<GetAllCardResponseModel>()

    override fun getAllCards() {
        getAllCardUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                accountsSuccess.onNext(it)
            },{
                //some error int
                error.onNext(1992)
                it.printStackTrace()
            })
    }

    override fun accountsSuccess() = accountsSuccess

}