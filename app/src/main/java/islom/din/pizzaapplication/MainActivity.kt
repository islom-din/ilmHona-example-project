package islom.din.pizzaapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2.FoodCategory
import com.example.lesson2.FoodCategoryAdapter
import com.example.recyclerview_itemclick.DataSource
import com.example.recyclerview_itemclick.FoodAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import islom.din.pizzaapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var chooseCityBottomSheet: BottomSheetDialog? = null
    private var aboutBannerBottomSheet: BottomSheetDialog? = null

    private lateinit var categories: RecyclerView
    private lateinit var foods: RecyclerView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
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

        // !!!
        setupCategoriesList()
        setupFoodList(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupCategoriesList() {
        categories = findViewById(R.id.categories_list)
        val listB = getCategory()
        val adapter2 = FoodCategoryAdapter()
        adapter2.submitList(listB)
        adapter2.onItemClick = { id ->

            for (item in listB) {
                if(item.id == id) {
                    item.isSelected = true
                } else {
                    item.isSelected = false
                }
//                item.isSelected = item.id == id
            }

            adapter2.submitList(listB)
            updateFoodList(id)
            setupFoodList(id)
        }
        categories.adapter = adapter2
    }

    private fun setupFoodList(categoryId: Int) {
        //1) Найти список
        val data = DataSource()
        data.category = categoryId
        foods = findViewById(R.id.foods_list_rv)
        val myList = data.getList()
        val adapter = FoodAdapter(myList)
        foods.adapter = adapter

        adapter.setOnItemClickListener(object: FoodAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, ItemActivity::class.java)
                intent.putExtra("NAME", myList[position].name)
                intent.putExtra("DESCRIPTION", myList[position].description)
                intent.putExtra("PRICE", myList[position].price)
                intent.putExtra("IMAGE", myList[position].imageId)
                startActivity(intent)
            }
        })
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

    private fun getCategory(): MutableList<FoodCategory> {
        val listB = getButtonText()
        val categoryList: MutableList<FoodCategory> = mutableListOf(
            FoodCategory(1, listB[0], true),
            FoodCategory(2, listB[1], false),
            FoodCategory(3, listB[2], false),
            FoodCategory(4, listB[3], false),
            FoodCategory(5, listB[4], false),
//            FoodCategory(6, listB[5], false),
//            FoodCategory(7, listB[6], false)

        )
        return categoryList
    }

    private fun getButtonText(): ArrayList<String> {
        val listButtonText = arrayListOf<String>(
            "Комбо",
            "Закуски",
            "Напитки",
            "Пицца",
            "Десерты",
            "Соусы",
            "Другие товары"
        )
        return listButtonText
    }

    private fun updateFoodList(id: Int) {
        //TODO: something
    }
}