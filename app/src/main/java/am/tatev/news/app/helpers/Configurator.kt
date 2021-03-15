package am.tatev.news.app.helpers

import am.tatev.news.BuildConfig
import am.tatev.news.data.di.DataModule
import am.tatev.news.data.di.DbModule
import am.tatev.news.data.di.NetworkModule
import am.tatev.news.presentation.di.PresentationModule
import android.content.Context
import android.os.Build
import com.google.android.gms.security.ProviderInstaller
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class Configurator {

    fun configureAppInit(context: Context) {
        initKoin(context)
        initTimber()
        installSecurityIfNeeded(context)
    }

    private fun initKoin(context: Context) {
        val appComponent = listOf(
            DbModule,
            DataModule,
            NetworkModule,
            PresentationModule
        )

        startKoin {
            androidContext(context)
            modules(appComponent)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.uprootAll()
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun installSecurityIfNeeded(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            try {
                ProviderInstaller.installIfNeeded(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}