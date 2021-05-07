package com.devhypercoder.aicuisine.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.data.Category

class CategoryAdapter(private val categoryList: ArrayList<Category>, private val onClick: (Category) -> Unit) :
        RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val onClick: (Category) -> Unit) :
            RecyclerView.ViewHolder(itemView) {
        private var currentCategory: Category? = null
        private val textView: TextView = itemView.findViewById(R.id.category_text)

        init {
            itemView.setOnClickListener {
                currentCategory?.let {
                    onClick(it)
                }
            }
        }

        fun bind(category: Category) {
            currentCategory = category
            textView.text = category.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}
