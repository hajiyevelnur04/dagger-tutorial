package com.eebros.daggertutorial.remote

import com.eebros.daggertutorial.BuildConfig

class Constants{
    companion object{
        const val CHARSET = "Charset: UTF-8"
        const val CONTENT_TYPE_JSON = "content-type: application/json"
        const val ACCEPT = "Accept: application/json"
        const val BASIC_AUTH = "authorization: test"
        const val BASE_URL = BuildConfig.BASIC_URL
        const val PEM_FILE = BuildConfig.PEM_FILE_NAME
    }
}