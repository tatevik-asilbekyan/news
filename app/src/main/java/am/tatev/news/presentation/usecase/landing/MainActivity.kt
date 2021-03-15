package am.tatev.news.presentation.usecase.landing

import am.tatev.news.databinding.ActivityMainBinding
import am.tatev.news.helpers.showFragment
import am.tatev.news.presentation.usecase.news.NewsFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(binding.container.id, NewsFragment(), TAG)
    }

    companion object {
        private const val TAG = "news"
    }
}