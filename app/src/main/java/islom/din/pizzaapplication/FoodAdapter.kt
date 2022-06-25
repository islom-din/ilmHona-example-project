package com.example.recyclerview_itemclick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import islom.din.pizzaapplication.Food
import islom.din.pizzaapplication.R
import islom.din.pizzaapplication.databinding.FoodItemBinding

class FoodAdapter:
    RecyclerView.Adapter<FoodAdapter.FoodHolder>(){

    var dataList: List<Food> = emptyList()
    var onItemClick: ((Food) -> Unit)? = null

    fun submitList(newList: List<Food>) {
        dataList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val adapterLayout: View = layoutInflater.inflate(
            R.layout.food_item,
            parent, false
        )
        return FoodHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataList.size

    inner class FoodHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = FoodItemBinding.bind(view)

        fun bind(food: Food) {
            binding.imageFood.setImageResource(food.imageId)
            binding.nameFood.text = food.name
            binding.descriptionFood.text = food.description
            binding.priceFood.text = "от ${food.price} TJS"
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition])
            }
        }
    }
}