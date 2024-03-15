package bangkit.kiki.androidbeginnerfinalproject

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import bangkit.kiki.androidbeginnerfinalproject.databinding.ActivityDetailFruitBinding
import com.bumptech.glide.Glide
import java.util.*

class DetailFruitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFruitBinding

    companion object {
        const val FRUIT_OBJECT_KEY = "fruit_object"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fruit)

        binding = ActivityDetailFruitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.iconProfile.visibility = View.GONE

        binding.toolbar.iconArrowBack.setOnClickListener{
            finish()
        }

        val fruit = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(FRUIT_OBJECT_KEY, Fruit::class.java)
        } else {
            intent.getParcelableExtra<Fruit>(FRUIT_OBJECT_KEY)
        }

        Glide.with(this).load(fruit?.photo).into(binding.imgDetailItemPhoto)
        binding.tvDetailItemName.text = fruit?.name
        binding.tvDetailItemScientificName.text = fruit?.scientificName
        binding.tvDetailItemDescription.text = fruit?.description

        val nutrition = fruit?.nutrition?.replace(", and ", ", ")?.replace(".", "")
        val nutritions = nutrition?.split(", ")?.toTypedArray()
        val nutritionText = StringBuilder()

        if (nutritions != null) {
            for (nutrition in nutritions) {
                nutritionText.append("- ${nutrition.replaceFirstChar { it.uppercase() }}\n")
            }
        }

        binding.tvDetailItemNutritions.text = nutritionText.trimEnd().toString()

        val benefits = fruit?.benefit?.split(", ")?.toTypedArray()
        val benefitText = StringBuilder()

        if (benefits != null) {
            for (benefit in benefits) {
                benefitText.append("- $benefit\n")
            }
        }

        binding.tvDetailItemBenefits.text = benefitText.trimEnd().toString()

        binding.actionShare.setOnClickListener{
            val url = fruit?.photo
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(shareIntent, "Share using..."))
        }
    }
}