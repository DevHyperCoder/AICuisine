package com.devhypercoder.aicuisine.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.data.Ingredient

class IngredientGridAdapter(
    private val context: Context,
    private val ingredients: ArrayList<Ingredient>
) : BaseAdapter() {

    override fun getCount(): Int {
        return ingredients.size
    }

    override fun getItem(position: Int): Any {
        return ingredients[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        if (view == null) {
            val layoutIngredient = LayoutInflater.from(context)
            view = layoutIngredient.inflate(R.layout.ingredient_item, null)
        }

        val imageView = view!!.findViewById<ImageView>(R.id.ingredient_image)
        val nameText = view.findViewById<TextView>(R.id.ingredient_name)
        val amtText = view.findViewById<TextView>(R.id.ingredient_amt)

        val ingredient = getItem(position) as Ingredient

        nameText.text = ingredient.name
        amtText.text = ingredient.amount

        Glide.with(context).load(ingredient.image).into(imageView)

        return view
    }
}