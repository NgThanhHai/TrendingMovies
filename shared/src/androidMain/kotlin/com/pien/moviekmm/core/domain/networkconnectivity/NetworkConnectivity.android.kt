package com.pien.moviekmm.core.domain.networkconnectivity
import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat.getSystemService

actual class NetworkConnectivity(private val context: Context) {
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    actual fun isNetworkConnected(): Boolean {
        val activeNetworkInfo: NetworkInfo? = (getSystemService(context, ConnectivityManager::class.java))?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}