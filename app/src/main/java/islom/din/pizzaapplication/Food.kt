package islom.din.pizzaapplication

//TODO: learn about data-class
data class Food(
    val imageId: Int,
    val name: String,
    val description: String,
    val price: Int,
    val category: Int,
    val additionalComponents: List<Food>? = null//TODO: список всех элементов из которых состоит
    // комбо
)