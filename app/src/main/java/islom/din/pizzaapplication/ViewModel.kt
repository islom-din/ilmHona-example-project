package islom.din.pizzaapplication

import androidx.lifecycle.ViewModel
import com.example.lesson2.FoodCategory
import com.example.recyclerview_itemclick.DataSource

//VIEW MODEL
class MainViewModel : ViewModel() {

    private val dataSource = DataSource()

    var categories = mutableListOf(
        FoodCategory(1, "Комбо", false),
        FoodCategory(2, "Закуски", false),
        FoodCategory(3, "Напитки", false),
        FoodCategory(4, "Пицца", false),
        FoodCategory(5, "Десерты", false),
    )

    fun getUpdatedCategories(selectedCategoryId: Int): List<FoodCategory> {
        return categories.map {
                FoodCategory(
                    id = it.id,
                    name = it.name,
                    isSelected = it.id == selectedCategoryId
                )
        }
    }

    fun getFoodById(id: Int = 1): List<Food> {
        return dataSource.getList(id)
    }
}