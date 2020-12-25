package com.marcokosan.pagsegurotest.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marcokosan.pagsegurotest.data.TestData.BEER_LIST
import com.marcokosan.pagsegurotest.factory.RepositoryFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

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
    fun fetchBeers_loaded() = runBlockingTest {
        createViewModel().let {
            assertEquals(BEER_LIST, it.beers.value)
            assertEquals(false, it.loading.value)
        }
    }

    @Test
    fun fetchBeers_failed() = runBlockingTest {
        val viewModel = createViewModel(error = true)
        viewModel.let {
            it.fetchBeers()

            assertNull(it.beers.value)
            assertNotNull(it.failure.value)
            assertEquals(false, it.loading.value)
        }
    }

    private fun createViewModel(error: Boolean = false): HomeViewModel {
        return HomeViewModel(RepositoryFactory.FakeBeerRepository(error = error))
    }
}