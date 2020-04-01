package com.eebros.daggertutorial.repository

import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import com.eebros.daggertutorial.remote.service.MainApiServiceProvider
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface MainRepositoryType{
    fun getAllCards() : PublishSubject<GetAllCardResponseModel>
}

class MainRepository @Inject constructor(
    private val serviceProvider: MainApiServiceProvider
) : MainRepositoryType {

    override fun getAllCards(): PublishSubject<GetAllCardResponseModel> {
        return serviceProvider.getInstance().getAllCards()
    }

}