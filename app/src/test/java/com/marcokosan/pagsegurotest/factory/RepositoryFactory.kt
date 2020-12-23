package com.marcokosan.pagsegurotest.factory

import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.data.TestData.beer
import com.marcokosan.pagsegurotest.data.TestData.beerList
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.provider.data.source.BeerRepository

object RepositoryFactory {

    class FakeBeerRepository(
        private val error: Boolean = false
    ) : BeerRepository {
        override suspend fun getBeers(page: Int, perPage: Int): Result<List<Beer>> {
            return if (error) Result.FAILURE else Result.Success(beerList)
        }
        override suspend fun getBeerDetail(id: Long): Result<Beer> {
            return if (error) Result.FAILURE else Result.Success(beer)
        }
    }
}