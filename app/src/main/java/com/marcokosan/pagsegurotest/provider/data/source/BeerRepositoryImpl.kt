package com.marcokosan.pagsegurotest.provider.data.source

import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.provider.data.source.remote.BeerRemoteDataSource

class BeerRepositoryImpl(
    private val remoteSource: BeerRemoteDataSource
) : BeerRepository {

    override suspend fun getBeers(page: Int, perPage: Int): Result<List<Beer>> {
        return remoteSource.getBeers(page, perPage)
    }

    override suspend fun getBeerDetail(id: Long): Result<Beer> {
        return remoteSource.getBeerDetails(id)
    }
}