package com.marcokosan.pagsegurotest.provider.data.response

import com.marcokosan.pagsegurotest.model.Beer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "image_url") val imageUrl: String? = null,
    @Json(name = "abv") val abv: Float? = null,
    @Json(name = "ibu") val ibu: Float? = null,
    @Json(name = "tagline") val tagline: String? = null,
    @Json(name = "description") val description: String? = null
) {

    fun toBeer(): Beer {
        return Beer(
            id = id,
            name = name,
            imageUrl = imageUrl,
            abv = abv ?: 0.0F,
            ibu = ibu ?: 0.0F,
            tagline = tagline,
            description = description
        )
    }
}