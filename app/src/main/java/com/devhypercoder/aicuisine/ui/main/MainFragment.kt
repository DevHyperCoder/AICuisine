package com.devhypercoder.aicuisine.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.data.Category
import com.devhypercoder.aicuisine.data.getCategoryListFB
import com.devhypercoder.aicuisine.databinding.MainFragmentBinding
import com.devhypercoder.aicuisine.ui.StateViewModel
import com.devhypercoder.aicuisine.ui.UserViewModel
import com.devhypercoder.aicuisine.ui.adapters.CategoryAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment() {
    private val categories = ArrayList<Category>()
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

        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        Firebase.firestore.firestoreSettings = settings

        // TODO fix later pls
//        binding.logoutBtn.setOnClickListener {
//            auth.signOut()
//            userViewModel.authStatus.value = false
//            userViewModel.email.value = ""
//
//        }

        val categoryAdapter = CategoryAdapter(requireContext(), categories) {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
            stateViewModel.selectedCategory.value = it
            navController.navigate(R.id.action_mainFragment_to_menuFragment)
        }

        getCategoryListFB() { categoryList ->
            categories.clear()
            categories.addAll(categoryList)
            categoryAdapter.notifyDataSetChanged()
        }

        binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.categoryRecyclerview.adapter = categoryAdapter

        return binding.root
    }
}