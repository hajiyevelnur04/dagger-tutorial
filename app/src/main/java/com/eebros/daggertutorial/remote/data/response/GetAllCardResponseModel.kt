package com.eebros.daggertutorial.remote.data.response

data class GetAllCardResponseModel (
    val id: String,
    val name: String,
    val type: String,
    val desc: String,
    val race: String,
    val archetype: String,
    val views: String,
    val formats: String,
    val card_sets: CardSets,
    val card_images: CardImages,
    val card_prices: CardPrices
)
