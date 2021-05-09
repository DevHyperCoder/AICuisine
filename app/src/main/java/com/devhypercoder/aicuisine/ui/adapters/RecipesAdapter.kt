package com.devhypercoder.aicuisine.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.data.Recipe

class RecipesAdapter(
    private val recipeList: ArrayList<Recipe>,
    private val onClick: (Recipe) -> Unit
) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val onClick: (Recipe) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var currentRecipe: Recipe? = null
        private val textView: TextView = itemView.findViewById(R.id.recipie_name)

        init {
            itemView.setOnClickListener {
                currentRecipe?.let {
                    Log.e("AICU", "clicked in receipeies adapter: ")
                    onClick(it)
                }
            }
        }

        fun bind(menu: Recipe) {
            currentRecipe = menu
            textView.text = menu.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}