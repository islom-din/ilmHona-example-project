package com.example.lesson2

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import islom.din.pizzaapplication.R

class FoodCategoryAdapter : RecyclerView.Adapter<FoodCategoryAdapter.ButtonViewHolder>() {

    /* --------------------------------------------
    *  public
    * --------------------------------------------*/

    var onItemClick: ((Int) -> Unit)? = null
    private var listButton: List<FoodCategory> = emptyList()

    fun submitList(newList: List<FoodCategory>) {
        listButton = newList
        notifyDataSetChanged() // это тяжело
    }

    /* --------------------------------------------
    *  override
    * --------------------------------------------*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_category, parent, false)
        )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.button.text = listButton[position].name

        if (listButton[position].isSelected) {
            holder.button.setBackgroundResource(R.drawable.button_click_active_design)
            holder.button.setTextColor(
                holder.itemView.resources.getColor(
                    R.color.color_text_categoryfilter_selected,
                    null
                )
            )
        } else {
            holder.button.setBackgroundResource(R.drawable.button_click_unactive)
            holder.button.setTextColor(
                holder.itemView.resources.getColor(
                    R.color.color_text_categoryfilter_non,
                    null
                )
            )

        }
    }

    override fun getItemCount(): Int {
        return listButton.size
    }

    /* --------------------------------------------
    *  viewHolder
    * --------------------------------------------*/

    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: TextView = view.findViewById(R.id.rvButton)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(listButton[adapterPosition].id)
            }
        }
    }
}