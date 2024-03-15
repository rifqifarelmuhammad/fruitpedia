package bangkit.kiki.androidbeginnerfinalproject

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.kiki.androidbeginnerfinalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.iconArrowBack.visibility = View.GONE
        binding.toolbar.iconProfile.setOnClickListener{
            val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(moveIntent)
        }

        binding.rvFruits.setHasFixedSize(true)

        list.addAll(getListFruits())
        showRecyclerList()
    }

    private fun getListFruits(): ArrayList<Fruit> {
        val dataName = resources.getStringArray(R.array.fruit_name_data)
        val dataScientificName = resources.getStringArray(R.array.fruit_scientific_name_data)
        val dataDescription = resources.getStringArray(R.array.fruit_description_data)
        val dataNutrition = resources.getStringArray(R.array.fruit_nutrition_data)
        val dataBenefit = resources.getStringArray(R.array.fruit_benefit_data)
        val dataPhoto = resources.getStringArray(R.array.fruit_photo_data)

        val listFruit = ArrayList<Fruit>()
        for (i in dataName.indices) {
            val fruit = Fruit(dataName[i], dataScientificName[i], dataDescription[i], dataNutrition[i], dataBenefit[i], dataPhoto[i])
            listFruit.add(fruit)
        }

        return  listFruit
    }

    private fun showRecyclerList() {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rvFruits.layoutManager = LinearLayoutManager(this)
        } else {
            binding.rvFruits.layoutManager = GridLayoutManager(this, 2)
        }

        val listFruitAdapter = ListFruitAdapter(list)
        listFruitAdapter.setOnItemClickCallback(object : ListFruitAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Fruit) {
                val moveIntent = Intent(this@MainActivity, DetailFruitActivity::class.java)
                moveIntent.putExtra(DetailFruitActivity.FRUIT_OBJECT_KEY, data)
                startActivity(moveIntent)
            }
        })
        binding.rvFruits.adapter = listFruitAdapter
    }
}