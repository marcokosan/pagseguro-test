package com.marcokosan.pagsegurotest.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.marcokosan.pagsegurotest.MainApplication

object NetworkUtils {

    @Suppress("DEPRECATION")
    fun isConnected(): Boolean {
        val connectivityManager = MainApplication.context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            network ?: return false

            return connectivityManager.getNetworkCapabilities(network)?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnectedOrConnecting ?: false
        }
    }
}