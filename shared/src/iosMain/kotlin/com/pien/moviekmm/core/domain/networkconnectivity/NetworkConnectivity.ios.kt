package com.pien.moviekmm.core.domain.networkconnectivity

actual class NetworkConnectivity {

    actual fun isNetworkConnected(): Boolean {
        return true //mock for IOS first, implement later
    }
}