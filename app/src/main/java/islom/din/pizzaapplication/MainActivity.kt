package islom.din.pizzaapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2.FoodCategory
import com.example.lesson2.FoodCategoryAdapter
import com.example.recyclerview_itemclick.DataSource
import com.example.recyclerview_itemclick.FoodAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import islom.din.pizzaapplication.databinding.ActivityItemBinding
import islom.din.pizzaapplication.databinding.ActivityMainBinding

// VIEW
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // ViewModel для данной активити
    private lateinit var viewModel: MainViewModel

    // Адаптеры для списков
    private val categoriesAdapter = FoodCategoryAdapter2()
    private val foodAdapter = FoodAdapter()

    // Bottom sheets
    private var chooseCityBottomSheet: BottomSheetDialog? = null
    private var aboutBannerBottomSheet: BottomSheetDialog? = null

    /** --------------------------------------------------------------------------------------------
     *  lifecycle
     * -------------------------------------------------------------------------------------------*/

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.foodsListRv.itemAnimator = null
        binding.foodsListRv.adapter = foodAdapter
        binding.categoriesList.adapter = categoriesAdapter

        setupListeners()
        setupCategoriesList()

        setupCityBottomSheet()
        setupBannerBottomSheet()
        binding.cityName.text = "Душанбе"
        binding.text1.setOnClickListener {
            binding.text2.setBackgroundResource(R.color.darkGray)
            binding.address2.visibility = View.INVISIBLE
            binding.address1.visibility = View.VISIBLE
            binding.text1.setBackgroundResource(R.drawable.button_shape)
        }
        binding.text2.setOnClickListener {
            binding.text2.setBackgroundResource(R.drawable.button_shape)
            binding.text1.setBackgroundResource(R.color.darkGray)
            binding.address1.visibility = View.INVISIBLE
            binding.address2.visibility = View.VISIBLE
        }

        binding.cityName.setOnClickListener {
            chooseCityBottomSheet?.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /** --------------------------------------------------------------------------------------------
     *  private
     * -------------------------------------------------------------------------------------------*/

    private fun setupListeners() {
        categoriesAdapter.onItemClick = { setupCategoriesList(it) }
        foodAdapter.onItemClick = {
            val intent = Intent(this@MainActivity, ItemActivity::class.java)
            intent.putExtra("NAME", it.name)
            intent.putExtra("DESCRIPTION", it.description)
            intent.putExtra("PRICE", it.price)
            intent.putExtra("IMAGE", it.imageId)
            startActivity(intent)
        }
    }

    private fun setupCategoriesList(selectedCategoryId: Int = 1) {
        categoriesAdapter.submitList(
            viewModel.getUpdatedCategories(selectedCategoryId)
        )
        setupFoodList(selectedCategoryId)
    }

    private fun setupFoodList(categoryId: Int) {
        val foods = viewModel.getFoodById(categoryId)
        foodAdapter.submitList(foods)
    }

    private fun setupCityBottomSheet() {
        chooseCityBottomSheet = BottomSheetDialog(this)
        chooseCityBottomSheet?.setContentView(R.layout.bottom_sheet_cities)
        val textInBottomSheet = chooseCityBottomSheet?.findViewById<TextView>(R.id.city_Dushanbe)
        textInBottomSheet?.setOnClickListener {
            val result = textInBottomSheet.text.toString() // это обработчик нажатия на
            // на элемент в bottomSheet

            binding.cityName.text = result
            chooseCityBottomSheet?.dismiss()// этот вызов скроет bottomSheet
        }

        val text2InBottomSheet = chooseCityBottomSheet?.findViewById<TextView>(R.id.city_Hissar)
        text2InBottomSheet?.setOnClickListener {
            val result = text2InBottomSheet.text.toString() // это обработчик нажатия на
            // на элемент в bottomSheet

            binding.cityName.text = result
            chooseCityBottomSheet?.dismiss()// этот вызов скроет bottomSheet
        }

        val text3InBottomSheet = chooseCityBottomSheet?.findViewById<TextView>(R.id.city_Khujand)
        text3InBottomSheet?.setOnClickListener {
            val result = text3InBottomSheet.text.toString() // это обработчик нажатия на
            // на элемент в bottomSheet

            binding.cityName.text = result
            chooseCityBottomSheet?.dismiss()// этот вызов скроет bottomSheet
        }

        val text4InBottomSheet = chooseCityBottomSheet?.findViewById<TextView>(R.id.city_Kulob)
        text4InBottomSheet?.setOnClickListener {
            val result = text4InBottomSheet.text.toString() // это обработчик нажатия на
            // на элемент в bottomSheet

            binding.cityName.text = result
            chooseCityBottomSheet?.dismiss()// этот вызов скроет bottomSheet
        }
    }

    private fun setupBannerBottomSheet() {
        aboutBannerBottomSheet = BottomSheetDialog(this)
        aboutBannerBottomSheet?.setContentView(R.layout.bottom_sheet_about_banner)
        var cardView1 = findViewById<CardView>(R.id.cardView1)
        cardView1?.setOnClickListener {
            aboutBannerBottomSheet?.show()
        }
    }
}