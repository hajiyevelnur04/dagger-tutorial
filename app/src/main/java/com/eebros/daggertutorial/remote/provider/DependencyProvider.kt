package com.eebros.daggertutorial.remote.provider

interface DependencyProvider<INSTANCE_TYPE> {
    fun getInstance(): INSTANCE_TYPE
}