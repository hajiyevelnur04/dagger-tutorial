package com.eebros.daggertutorial.repository

import javax.inject.Inject

interface MainRepositoryType{
    fun test()
}

class MainRepository @Inject constructor(
    private val sampleTest: String
) : MainRepositoryType {

    override fun test() {

    }

}