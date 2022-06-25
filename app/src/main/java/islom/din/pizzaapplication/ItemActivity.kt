package islom.din.pizzaapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import islom.din.pizzaapplication.R
import java.lang.RuntimeException

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)


        val foodItem: ImageView = findViewById(R.id.pizzaImage)
        val nameItem: TextView = findViewById(R.id.name)
        val descriptionItem: TextView = findViewById(R.id.description)
        val priceItem: TextView = findViewById(R.id.price)
        val backButton: ImageView = findViewById(R.id.backButton)

        val bundle: Bundle? = intent.extras
        val image = bundle?.getInt("IMAGE") ?: throw RuntimeException("Image required")
        val name = bundle.getString("NAME")
        val description = bundle.getString("DESCRIPTION")
        val price = bundle.getInt("PRICE")

        //.....

        foodItem.setImageResource(image)
        nameItem.text = name
        descriptionItem.text = description
        priceItem.text = "Добавить в корзину за ${price.toString()},00c"


        backButton?.setOnClickListener {
            finish()
        }

    }
}