package com.marcokosan.pagsegurotest.provider.api

import com.marcokosan.pagsegurotest.provider.data.response.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PagSeguroApi {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<BeerResponse>>

    @GET("beers/{id}")
    suspend fun getBeerDetail(@Path("id") id: Long): Response<Array<BeerResponse>>
}