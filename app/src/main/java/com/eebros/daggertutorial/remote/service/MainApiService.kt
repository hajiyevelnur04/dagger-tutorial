package com.eebros.daggertutorial.remote.service

import com.eebros.daggertutorial.remote.Constants
import com.eebros.daggertutorial.remote.data.request.TestRequestModel
import com.eebros.daggertutorial.remote.data.response.TestResponseModel
import com.eebros.daggertutorial.remote.provider.ServiceProvider
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface MainApiService {
    @Headers(
        Constants.CONTENT_TYPE_JSON,
        Constants.CHARSET,
        Constants.ACCEPT,
        Constants.BASIC_AUTH
    )
    @GET("somelink/test")
    fun someTest(
        @Body testRequestModel: TestRequestModel
    ): Single<TestResponseModel>
}

interface MainApiServiceProvider : ServiceProvider<MainApiService>