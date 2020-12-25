package com.marcokosan.pagsegurotest.response

import com.marcokosan.pagsegurotest.data.TestData.BEER_RESPONSE
import org.junit.Assert.assertEquals
import org.junit.Test

class ResponseTest {

    @Test
    fun beerResponse_to_Beer() {
        val beer = BEER_RESPONSE.toBeer()

        assertEquals(1L, beer.id)
        assertEquals("Name Test", beer.name)
        assertEquals("image_url_test", beer.imageUrl)
        assertEquals(12.34F, beer.abv)
        assertEquals(23.45F, beer.ibu)
        assertEquals("Tagline Test", beer.tagline)
        assertEquals("Description Test", beer.description)
    }
}