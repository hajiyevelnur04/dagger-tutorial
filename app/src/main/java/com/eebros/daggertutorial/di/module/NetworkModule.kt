package com.eebros.daggertutorial.di.module

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier

const val CONNECT_TIME_OUT = 300L
const val READ_TIMEOUT = 300L

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofitInstance(
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("http://www.test.com")
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()


    @Provides
    @Singleton
    fun provideHostnameVerifier(

    ) = HostnameVerifier{_, _ ->
        return@HostnameVerifier true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context,
        hostnameVerifier: HostnameVerifier
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .hostnameVerifier(hostnameVerifier)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}