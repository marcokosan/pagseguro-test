package com.marcokosan.pagsegurotest.provider.data.source

import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.model.Beer

interface BeerDataSource {

    suspend fun getBeers(page: Int, perPage: Int): Result<List<Beer>>

    suspend fun getBeerDetails(id: Long): Result<Beer>
}