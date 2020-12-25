package com.marcokosan.pagsegurotest.factory

import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.data.TestData.BEER
import com.marcokosan.pagsegurotest.data.TestData.BEER_LIST
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.provider.data.source.BeerRepository

object RepositoryFactory {

    class FakeBeerRepository(
        private val error: Boolean = false
    ) : BeerRepository {
        override suspend fun getBeers(page: Int, perPage: Int): Result<List<Beer>> {
            return if (error) Result.FAILURE else Result.Success(BEER_LIST)
        }
        override suspend fun getBeerDetail(id: Long): Result<Beer> {
            return if (error) Result.FAILURE else Result.Success(BEER)
        }
    }
}