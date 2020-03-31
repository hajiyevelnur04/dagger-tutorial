package com.eebros.daggertutorial.repository

import com.eebros.daggertutorial.remote.data.request.TestRequestModel
import com.eebros.daggertutorial.remote.data.response.TestResponseModel
import com.eebros.daggertutorial.remote.service.MainApiServiceProvider
import io.reactivex.Single
import javax.inject.Inject

interface MainRepositoryType{
    fun test(token: String) : Single<TestResponseModel>
}

class MainRepository @Inject constructor(
    private val serviceProvider: MainApiServiceProvider
) : MainRepositoryType {

    override fun test(token: String): Single<TestResponseModel> {
        val request =TestRequestModel("some text")
        return serviceProvider.getInstance().someTest(request)
    }

}