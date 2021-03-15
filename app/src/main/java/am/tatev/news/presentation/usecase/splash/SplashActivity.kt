package am.tatev.news.presentation.usecase.splash

import am.tatev.news.databinding.ActivitySplashBinding
import am.tatev.news.helpers.navigateToLandingAndFinish
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            navigateToLandingAndFinish(this)
        }, 1500)
    }
}