package com.devhypercoder.aicuisine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devhypercoder.aicuisine.data.Category
import com.devhypercoder.aicuisine.data.Recipe

class StateViewModel : ViewModel() {
    val selectedCategory: MutableLiveData<Category> = MutableLiveData()
    val selectedRecipie: MutableLiveData<Recipe> = MutableLiveData()
}