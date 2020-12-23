package com.marcokosan.pagsegurotest.data

import com.marcokosan.pagsegurotest.provider.data.response.BeerResponse

object TestData {

    const val FAKE_BEER_ID = 1L
    const val FAKE_BEER_NAME = "Fake beer name"

    val beerResponse = BeerResponse(
        id = FAKE_BEER_ID,
        name = FAKE_BEER_NAME,
        imageUrl = "image_url_test",
        abv = 12.34F,
        ibu = 23.45F,
        tagline = "Tagline Test",
        description = "Description Test",
    )

    val beer = beerResponse.toBeer()

    val beerList = listOf(beer)
}