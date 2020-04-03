package com.eebros.daggertutorial.remote.service

import com.eebros.daggertutorial.remote.Constants
import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import com.eebros.daggertutorial.remote.provider.ServiceProvider
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.*
import kotlin.collections.ArrayList

interface MainApiService {
    /*@Headers(
        Constants.CONTENT_TYPE_JSON,
        Constants.CHARSET,
        Constants.ACCEPT,
        Constants.BASIC_AUTH
    )*/
    @GET("cardinfo.php")
    fun getAllCards(
    ): Observable<ArrayList<GetAllCardResponseModel>>
}

interface MainApiServiceProvider : ServiceProvider<MainApiService>