package com.eebros.daggertutorial.usecases

import com.eebros.daggertutorial.repository.MainRepository
import javax.inject.Inject

class GetAllCardUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun execute() = mainRepository.getAllCards()
}