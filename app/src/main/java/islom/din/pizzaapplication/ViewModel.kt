package islom.din.pizzaapplication

import androidx.lifecycle.ViewModel
import com.example.lesson2.FoodCategory
import com.example.recyclerview_itemclick.DataSource

//VIEW MODEL
class MainViewModel : ViewModel() {

    private val dataSource = DataSource()

    var categories = mutableListOf(
        FoodCategory(1, "Комбо", true),
        FoodCategory(2, "Закуски", false),
        FoodCategory(3, "Напитки", false),
        FoodCategory(4, "Пицца", false),
        FoodCategory(5, "Десерты", false),
    )

    fun updateCategories(newCategories: List<FoodCategory>) {
        categories = newCategories.toMutableList()
    }

    fun getFoodById(id: Int = 1): List<Food> {
        return dataSource.getList(id)
    }

    override fun onCleared() {
        super.onCleared()
    }
}