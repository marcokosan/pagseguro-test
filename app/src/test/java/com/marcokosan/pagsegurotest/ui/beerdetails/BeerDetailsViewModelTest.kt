package com.marcokosan.pagsegurotest.ui.beerdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marcokosan.pagsegurotest.data.TestData.BEER
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BeerDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.cleanupTestCoroutines()
    }

    @Test
    fun setBeer() = runBlockingTest {
        createViewModel().let {
            it.setBeer(BEER)
            assertEquals(BEER, it.beerDetail.value)
        }
    }


    private fun createViewModel(): BeerDetailsViewModel {
        return BeerDetailsViewModel()
    }
}