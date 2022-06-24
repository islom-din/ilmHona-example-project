package islom.din.pizzaapplication

import com.example.lesson2.FoodCategory

//VIEW MODEL
class ViewModel {

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
}