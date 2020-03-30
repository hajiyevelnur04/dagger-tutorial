package com.eebros.daggertutorial

import android.content.Context
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