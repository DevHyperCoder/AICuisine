package com.devhypercoder.aicuisine.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.data.Category
import com.devhypercoder.aicuisine.data.Ingredient
import com.devhypercoder.aicuisine.data.Recipe
import com.devhypercoder.aicuisine.databinding.MainFragmentBinding
import com.devhypercoder.aicuisine.ui.StateViewModel
import com.devhypercoder.aicuisine.ui.UserViewModel
import com.devhypercoder.aicuisine.ui.adapters.CategoryAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private val stateViewModel: StateViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        val navController = findNavController()

        val auth = Firebase.auth
        userViewModel.authStatus.value = auth.currentUser !== null
        if (auth.currentUser !== null) {
            userViewModel.email.value = auth.currentUser!!.email
        }

        userViewModel.authStatus.observe(viewLifecycleOwner, { authStatus ->
            if (!authStatus) navController.navigate(R.id.registerFragment)
        })
        userViewModel.email.observe(viewLifecycleOwner, {
            binding.userName = it
        })

        // TODO fix later pls
//        binding.logoutBtn.setOnClickListener {
//            auth.signOut()
//            userViewModel.authStatus.value = false
//            userViewModel.email.value = ""
//
//        }


        val categoryAdapter = CategoryAdapter(getCategoryList()) {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
            stateViewModel.selectedCategory.value = it
            navController.navigate(R.id.action_mainFragment_to_menuFragment)
        }

        binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.categoryRecyclerview.adapter = categoryAdapter

        return binding.root
    }

    private fun getCategoryList(): ArrayList<Category> {
        val array: ArrayList<Category> = ArrayList()
        for (i in 0..3) {
            val recipieArray = ArrayList<Recipe>()
            val ingredientArray = ArrayList<Ingredient>()

            ingredientArray.add(Ingredient("", "1 pinch", "Pepper"))
            ingredientArray.add(Ingredient("", "3 pinch", "Salt"))

            recipieArray.add(Recipe("random recipie $i", ingredientArray, ""))
            recipieArray.add(Recipe("random another one", ingredientArray, ""))

            array.add(
                Category("$i category", "", "", recipieArray)
            )
        }
        return array
    }
}