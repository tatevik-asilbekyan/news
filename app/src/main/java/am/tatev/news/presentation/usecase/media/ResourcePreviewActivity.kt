package am.tatev.news.presentation.usecase.media

import am.tatev.news.databinding.ActivityResourcePreviewBinding
import am.tatev.news.helpers.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ResourcePreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResourcePreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResourcePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(EXTRA_RESOURCE_TYPE, RESOURCE_TYPE_UNSUPPORTED)
        val path = intent.getStringExtra(EXTRA_RESOURCE_PATH)
        val fragment = when (type) {
            RESOURCE_TYPE_IMAGE -> ImagePreviewFragment.newInstance(path)
            RESOURCE_TYPE_VIDEO -> VideoPreviewFragment.newInstance(path)
            else -> throw RuntimeException("Type unsupported")
        }
        showFragment(binding.container.id, fragment, TAG)
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    companion object {
        private const val TAG = "preview"
    }
}