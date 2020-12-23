package com.marcokosan.pagsegurotest.provider.data.source

import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.model.Beer

interface BeerRepository {

    suspend fun getBeers(page: Int, perPage: Int): Result<List<Beer>>

    suspend fun getBeerDetail(id: Long): Result<Beer>
}