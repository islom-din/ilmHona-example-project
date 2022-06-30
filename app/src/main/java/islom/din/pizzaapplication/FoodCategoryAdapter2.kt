package islom.din.pizzaapplication

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2.FoodCategory
import com.example.lesson2.FoodCategoryAdapter

/**
 * 1) Нужны ViewHolder и DiffUtilCallback
 * 2) Переопределить методы
 */
class FoodCategoryAdapter2 : ListAdapter<FoodCategory, FoodCategoryAdapter2.ButtonViewHolder>(
    FoodCategoryDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null

    /* --------------------------------------------
    *  diffUtil
    * --------------------------------------------*/

    private class FoodCategoryDiffUtil : DiffUtil.ItemCallback<FoodCategory>() {
        override fun areItemsTheSame(
            oldItem: FoodCategory,
            newItem: FoodCategory
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FoodCategory,
            newItem: FoodCategory
        ): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }

    /* --------------------------------------------
    *  override
    * --------------------------------------------*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryAdapter2.ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_category, parent, false)
        )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: FoodCategoryAdapter2.ButtonViewHolder, position: Int) {
        holder.button.text = getItem(position).name

        if (getItem(position).isSelected) {
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

    /* --------------------------------------------
    *  viewHolder
    * --------------------------------------------*/

    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: TextView = view.findViewById(R.id.rvButton)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).id)
            }
        }
    }
}