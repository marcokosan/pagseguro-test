package com.marcokosan.pagsegurotest

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.marcokosan.pagsegurotest.provider.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MainApplication : MultiDexApplication() {

    companion object {
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this.baseContext

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    dataSourceModule,
                    repositoryModule,
                    networkModule,
                    viewModelModule
                )
            )
            properties(
                mapOf(
                    Property.API_BASE_URL to BuildConfig.API_BASE_URL
                )
            )
        }
    }
}