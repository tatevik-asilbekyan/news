package am.tatev.news.app.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context.isNetworkAvailable(): Boolean {
    val manager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
            capabilities?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: false
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
            manager.allNetworks.any {
                val capabilities = manager.getNetworkCapabilities(it)
                capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            }
        }
        else -> {
            manager.activeNetworkInfo?.isConnected ?: false
        }
    }
}
