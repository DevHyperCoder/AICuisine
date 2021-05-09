package com.devhypercoder.aicuisine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devhypercoder.aicuisine.data.Category

class StateViewModel : ViewModel() {
    val selectedCategory: MutableLiveData<Category> = MutableLiveData()
}