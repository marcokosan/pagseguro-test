package com.marcokosan.pagsegurotest.data

import com.marcokosan.pagsegurotest.provider.data.response.BeerResponse

object TestData {

    const val FAKE_BEER_ID = 1L
    const val FAKE_BEER_NAME = "Fake beer name"
    const val FAKE_BEER_TAGLINE = "Tagline Test"

    val BEER_RESPONSE = BeerResponse(
        id = FAKE_BEER_ID,
        name = FAKE_BEER_NAME,
        imageUrl = "image_url_test",
        abv = 12.34F,
        ibu = 23.45F,
        tagline = FAKE_BEER_TAGLINE,
        description = "Description Test",
    )

    val BEER = BEER_RESPONSE.toBeer()

    val BEER_LIST = listOf(BEER)
}