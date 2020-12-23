package com.marcokosan.pagsegurotest.provider.mock

import com.marcokosan.pagsegurotest.model.Beer

@Suppress("MemberVisibilityCanBePrivate")
object InvestmentMock {

    val BEER_LIST get() = listOf(BEER, BEER_SKOL)

    val BEER get() = Beer(
        id = 1,
        name = "Fake beer name",
        imageUrl = "https://cdn-cosmos.bluesoft.com.br/products/8714800026956",
        abv = 89.9F,
        ibu = 8.6F,
        tagline = "Tagline Test",
        description = "Description Test"
    )

    val BEER_SKOL get() = Beer(
        id = 1,
        name = "Fake beer SKOL",
        imageUrl = "https://cdn-cosmos.bluesoft.com.br/products/8714800026956",
        abv = 89.9F,
        ibu = 8.6F,
        tagline = "Tagline Skol",
        description = "Description Skol"
    )
}