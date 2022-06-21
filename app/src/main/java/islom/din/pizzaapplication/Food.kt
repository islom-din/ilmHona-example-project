package islom.din.pizzaapplication

//TODO: learn about data-class
data class Food(
    val imageId: Int,
    val name: String,
    val description: String,
    val price: Int,
    val category: Int
)