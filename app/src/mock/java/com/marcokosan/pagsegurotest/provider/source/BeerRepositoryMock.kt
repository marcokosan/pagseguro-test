package com.marcokosan.pagsegurotest.provider.source

import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.provider.data.source.BeerRepository
import com.marcokosan.pagsegurotest.provider.mock.InvestmentMock
import com.marcokosan.pagsegurotest.util.safeIOCall

@Suppress("unused")
private const val SIMULATION_DELAY = 1000L

class BeerRepositoryMock : BeerRepository {

    override suspend fun getBeers(page: Int, perPage: Int) = safeIOCall {
        if (page == 0) {
            Result.FAILURE
        } else {
            Result.Success(InvestmentMock.BEER_LIST)
        }
    }

    override suspend fun getBeerDetail(id: Long) = safeIOCall {
        if (id == 0L) {
            Result.FAILURE
        } else {
            Result.Success(InvestmentMock.BEER)
        }
    }
}