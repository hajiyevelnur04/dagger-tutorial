package com.eebros.daggertutorial.remote.service

import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import com.eebros.daggertutorial.remote.provider.ServiceProvider
import io.reactivex.subjects.PublishSubject
import retrofit2.http.GET

interface MainApiService {
    /*@Headers(
        Constants.CONTENT_TYPE_JSON,
        Constants.CHARSET,
        Constants.ACCEPT,
        Constants.BASIC_AUTH
    )*/
    @GET("cardinfo.php")
    fun getAllCards(
    ): PublishSubject<GetAllCardResponseModel>
}

interface MainApiServiceProvider : ServiceProvider<MainApiService>