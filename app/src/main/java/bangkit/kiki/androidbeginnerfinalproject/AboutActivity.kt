package bangkit.kiki.androidbeginnerfinalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import bangkit.kiki.androidbeginnerfinalproject.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.iconProfile.visibility = View.GONE

        binding.toolbar.iconArrowBack.setOnClickListener{
            finish()
        }
    }
}