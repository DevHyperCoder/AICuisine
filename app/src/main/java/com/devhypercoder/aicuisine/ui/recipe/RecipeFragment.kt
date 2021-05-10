package com.devhypercoder.aicuisine.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.data.Recipe
import com.devhypercoder.aicuisine.ui.StateViewModel
import com.devhypercoder.aicuisine.ui.adapters.IngredientGridAdapter

class RecipeFragment : Fragment() {
    private val stateViewModel by activityViewModels<StateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe: Recipe = stateViewModel.selectedRecipie.value!!

        view.findViewById<TextView>(R.id.recipe_name_r).apply {
            text = recipe.name
        }

        // Load Image later
        val image = view.findViewById<ImageView>(R.id.recipe_image)

        Glide.with(requireContext()).load(recipe.image).into(image)

        val gridAdapter = IngredientGridAdapter(requireContext(), recipe.ingredients)

        val grid = view.findViewById<GridView>(R.id.ingredient_grid)

        grid.adapter = gridAdapter
    }
}