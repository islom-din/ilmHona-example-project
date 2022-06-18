package islom.din.pizzaapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2.FoodCategory
import com.example.lesson2.FoodCategoryAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private var chooseCityBottomSheet: BottomSheetDialog? = null
    private var aboutBannerBottomSheet: BottomSheetDialog? = null

    private var cityNameTextView: TextView? = null
    private var text1: TextView? = null
    private var text2: TextView? = null
    private var address1: TextView? = null
    private var address2: TextView? = null

    private lateinit var recyclerViewHorizontal: RecyclerView


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCityBottomSheet()
        setupBannerBottomSheet()

        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        address1 = findViewById(R.id.address1)
        address2 = findViewById(R.id.address2)
        cityNameTextView = findViewById(R.id.city_name)
        cityNameTextView?.text = "Душанбе"

        text1?.setOnClickListener {
            text2?.setBackgroundResource(R.color.darkGray)
            address2?.visibility = View.INVISIBLE
            address1?.visibility = View.VISIBLE
            text1?.setBackgroundResource(R.drawable.button_shape)
        }
        text2?.setOnClickListener {
            text2?.setBackgroundResource(R.drawable.button_shape)
            text1?.setBackgroundResource(R.color.darkGray)
            address1?.visibility = View.INVISIBLE
            address2?.visibility = View.VISIBLE
        }

        cityNameTextView?.setOnClickListener {
            chooseCityBottomSheet?.show()
        }

        recyclerViewHorizontal = findViewById(R.id.categories_list)
        val listB = getCategory()
        val adapter2 = FoodCategoryAdapter()
        adapter2.submitList(listB)
        adapter2.onItemClick = { id ->

            for (item in listB){
                item.isSelected = item.id == id
            }

            adapter2.submitList(listB)
            updateFoodList(id)
        }
        recyclerViewHorizontal.adapter = adapter2
    }

    private fun setupCityBottomSheet() {
        chooseCityBottomSheet = BottomSheetDialog(this)
        chooseCityBottomSheet?.setContentView(R.layout.bottom_sheet_cities)
        val textInBottomSheet = chooseCityBottomSheet?.findViewById<TextView>(R.id.city_Dushanbe)
        textInBottomSheet?.setOnClickListener {
            val result = textInBottomSheet.text.toString() // это обработчик нажатия на
            // на элемент в bottomSheet

            cityNameTextView?.text = result
            chooseCityBottomSheet?.dismiss()// этот вызов скроет bottomSheet
        }

        val text2InBottomSheet = chooseCityBottomSheet?.findViewById<TextView>(R.id.city_Hissar)
        text2InBottomSheet?.setOnClickListener {
            val result = text2InBottomSheet.text.toString() // это обработчик нажатия на
            // на элемент в bottomSheet

            cityNameTextView?.text = result
            chooseCityBottomSheet?.dismiss()// этот вызов скроет bottomSheet
        }

        val text3InBottomSheet = chooseCityBottomSheet?.findViewById<TextView>(R.id.city_Khujand)
        text3InBottomSheet?.setOnClickListener {
            val result = text3InBottomSheet.text.toString() // это обработчик нажатия на
            // на элемент в bottomSheet

            cityNameTextView?.text = result
            chooseCityBottomSheet?.dismiss()// этот вызов скроет bottomSheet
        }

        val text4InBottomSheet = chooseCityBottomSheet?.findViewById<TextView>(R.id.city_Kulob)
        text4InBottomSheet?.setOnClickListener {
            val result = text4InBottomSheet.text.toString() // это обработчик нажатия на
            // на элемент в bottomSheet

            cityNameTextView?.text = result
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
            FoodCategory(6, listB[5], false),
            FoodCategory(7, listB[6], false)

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