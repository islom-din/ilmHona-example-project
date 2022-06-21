package com.example.recyclerview_itemclick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import islom.din.pizzaapplication.Food
import islom.din.pizzaapplication.R
import islom.din.pizzaapplication.databinding.FoodItemBinding

class FoodAdapter(private val dataList: List<Food>):
    RecyclerView.Adapter<FoodAdapter.FoodHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val adapterLayout: View = LayoutInflater.from(parent.context).inflate(
            R.layout.food_item,
            parent, false)
        return FoodHolder(adapterLayout, mListener)
    }


    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataList.size

    class FoodHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        private val binding = FoodItemBinding.bind(view)

        fun bind(food: Food) {
            binding.imageFood.setImageResource(food.imageId)
            binding.nameFood.text = food.name
            binding.descriptionFood.text = food.description
            binding.priceFood.text = "от ${food.price} TJS"
        }

//        val image: ImageView = view.findViewById(R.id.image_food)
//        val name: TextView = view.findViewById(R.id.name_food)
//        val description: TextView = view.findViewById(R.id.description_food)
//        val price: TextView = view.findViewById(R.id.price_food)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}