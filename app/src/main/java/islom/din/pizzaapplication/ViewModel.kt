package islom.din.pizzaapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson2.FoodCategory
import com.example.recyclerview_itemclick.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

//VIEW MODEL
class MainViewModel : ViewModel() {

    private val dataSource = DataSource()

//    private val scope = CoroutineScope(Dispatchers.Default)

    var categories = mutableListOf(
        FoodCategory(1, "Комбо", false),
        FoodCategory(2, "Закуски", false),
        FoodCategory(3, "Напитки", false),
        FoodCategory(4, "Пицца", false),
        FoodCategory(5, "Десерты", false),
    )

    fun getUpdatedCategories(selectedCategoryId: Int): List<FoodCategory> {
        viewModelScope.launch(Dispatchers.IO) {

        }
        return categories.map {
            val newObj = FoodCategory(
                    id = it.id,
                    name = it.name,
                    isSelected = it.id == selectedCategoryId
                )
            newObj
        }
    }

    fun getFoodById(id: Int = 1): List<Food> {
        return dataSource.getList(id)
    }

//    override fun onCleared() {
//        super.onCleared()
//        scope.cancel()
//    }
}