package com.marcokosan.pagsegurotest.model

data class Beer(
    val id: Long,
    val name: String,
    val imageUrl: String?,
    val abv: Float,
    val ibu: Float,
    val tagline: String?,
    val description: String?,
)

