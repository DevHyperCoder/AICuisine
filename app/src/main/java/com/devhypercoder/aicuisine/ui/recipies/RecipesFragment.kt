package com.devhypercoder.aicuisine.ui.recipies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.ui.StateViewModel
import com.devhypercoder.aicuisine.ui.adapters.RecipesAdapter

class RecipesFragment : Fragment() {
    private val stateViewModel by activityViewModels<StateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = stateViewModel.selectedCategory.value!!
        val recipeList = category.recipes

        val adapter = RecipesAdapter(recipeList) {
            Log.e("AICUI", "onViewCreated: ${it.name}")
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.menu_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter


    }
}