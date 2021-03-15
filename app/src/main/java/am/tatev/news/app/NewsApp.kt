package am.tatev.news.app

import am.tatev.news.app.helpers.Configurator
import android.app.Application

class NewsApp : Application() {

    private val configurator = Configurator()

    override fun onCreate() {
        super.onCreate()
        configurator.configureAppInit(applicationContext)
    }
}