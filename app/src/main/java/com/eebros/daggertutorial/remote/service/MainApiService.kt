package com.eebros.daggertutorial.remote.service

import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import com.eebros.daggertutorial.remote.provider.ServiceProvider
import io.reactivex.Observable
import retrofit2.http.GET

interface MainApiService {
    @GET("cardinfo.php")
    fun getAllCards(
    ): Observable<ArrayList<GetAllCardResponseModel>>
}

interface MainApiServiceProvider : ServiceProvider<MainApiService>